package com.sinosoft.ie.booster.auth.config;

import com.sinosoft.ie.booster.auth.component.BoosterPasswordTokenGranter;
import com.sinosoft.ie.booster.auth.component.BoosterWebResponseExceptionTranslator;
import com.sinosoft.ie.booster.auth.provider.BoosterClientDetailsService;
import com.sinosoft.ie.booster.auth.provider.BoosterUserDetailsService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 认证服务器配置
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final DataSource dataSource;

	private final BoosterUserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	private final RedisConnectionFactory redisConnectionFactory;

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		BoosterClientDetailsService clientDetailsService = new BoosterClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancer()).userDetailsService(userDetailsService)
				.authenticationManager(authenticationManager).reuseRefreshTokens(false)
				.pathMapping("/oauth/confirm_access", "/token/confirm_access")
				.exceptionTranslator(new BoosterWebResponseExceptionTranslator())
				// 需要放在最末尾，因为依赖endpoints中的参数
				.tokenGranter(tokenGranter(endpoints));
		;
	}

	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(CacheConstants.PROJECT_OAUTH_ACCESS);
		return tokenStore;
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			//client_credentials授权模式时为null
			if (authentication.getUserAuthentication() != null) {
				final Map<String, Object> additionalInfo = new HashMap<>(4);
				BoosterUser boosterUser = (BoosterUser) authentication.getUserAuthentication().getPrincipal();
				additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
				additionalInfo.put(SecurityConstants.DETAILS_USER_ID, boosterUser.getId());
				additionalInfo.put(SecurityConstants.DETAILS_USERNAME, boosterUser.getUsername());
				additionalInfo.put(SecurityConstants.DETAILS_DEPT_ID, boosterUser.getDeptId());
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			}
			return accessToken;
		};
	}

	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		List<TokenGranter> list = new ArrayList<>();
		// 授权码模式
		list.add(new AuthorizationCodeTokenGranter(
				endpoints.getTokenServices(),
				endpoints.getAuthorizationCodeServices(),
				endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory()));
		// 刷新token模式、
		list.add(new RefreshTokenGranter(
				endpoints.getTokenServices(),
				endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory()));
		// 简化模式
		list.add(new ImplicitTokenGranter(
				endpoints.getTokenServices(),
				endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory()));
		// 客户端模式
		list.add(new ClientCredentialsTokenGranter(
				endpoints.getTokenServices(),
				endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory()));
		// 密码模式
		if (authenticationManager != null) {
			list.add(new BoosterPasswordTokenGranter(
					authenticationManager,
					endpoints.getTokenServices(),
					endpoints.getClientDetailsService(),
					endpoints.getOAuth2RequestFactory(),
					userDetailsService,
					redisTemplate));
		}
		return new CompositeTokenGranter(list);
	}
}
