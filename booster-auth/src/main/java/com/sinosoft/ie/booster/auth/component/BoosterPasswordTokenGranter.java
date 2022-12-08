package com.sinosoft.ie.booster.auth.component;

import com.sinosoft.ie.booster.auth.provider.BoosterUserDetailsService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 改造 {@link ResourceOwnerPasswordTokenGranter} 增加自定义验证规则
 *
 * @author haocy
 * @since 2021.11.25
 */
public class BoosterPasswordTokenGranter extends AbstractTokenGranter {

	private static final String GRANT_TYPE = "password";

	private final AuthenticationManager authenticationManager;

	private final BoosterUserDetailsService userDetailsService;

	private final RedisTemplate<String, Object> redisTemplate;

	public BoosterPasswordTokenGranter(
			AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices,
			ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory,
			BoosterUserDetailsService userDetailsService,
			RedisTemplate<String, Object> redisTemplate) {
		this(authenticationManager, tokenServices, clientDetailsService, requestFactory,
				GRANT_TYPE, userDetailsService, redisTemplate);
	}

	protected BoosterPasswordTokenGranter(
			AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices,
			ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory,
			String grantType,
			BoosterUserDetailsService userDetailsService,
			RedisTemplate<String, Object> redisTemplate) {
		super(tokenServices, clientDetailsService, requestFactory, grantType);
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.redisTemplate = redisTemplate;
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
		String username = parameters.get("username");
		String password = parameters.get("password");
		// Protect from downstream leaks of password
		parameters.remove("password");

		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
		String lockUserKey = CacheConstants.USER_LOCK_DETAILS + username;
		try {
			userAuth = authenticationManager.authenticate(userAuth);
		}
		catch (AccountStatusException ase) {
			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
			throw new InvalidGrantException(ase.getMessage());
		}
		catch (BadCredentialsException e) {
			// 用户登录如果密码错误达到最大错误次数将会自动锁定
			Long errorCount = redisTemplate.opsForValue().increment(lockUserKey);
			redisTemplate.expire(lockUserKey, SecurityConstants.USER_LOCK_TIME, TimeUnit.SECONDS);
			if(errorCount != null && errorCount >= SecurityConstants.ALLOW_MAX_PASSWORD_ERROR_COUNT){
				userDetailsService.lockUser(username);
				redisTemplate.delete(lockUserKey);
			}
			// If the username/password are wrong the spec says we should send 400/invalid grant
			throw new InvalidGrantException(e.getMessage()
					+ String.format("，您还剩%s次试错机会", SecurityConstants.ALLOW_MAX_PASSWORD_ERROR_COUNT - errorCount));
		}
		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + username);
		}

		//登录成功后删除记录当前用户的密码错误次数缓存
		redisTemplate.delete(lockUserKey);

		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		return new OAuth2Authentication(storedOAuth2Request, userAuth);
	}
}
