package com.sinosoft.ie.booster.auth.provider;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterGrantedAuthority;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author lengleng
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BoosterUserDetailsServiceImpl implements BoosterUserDetailsService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 用户密码登录
	 * @param username 用户名
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			return (BoosterUser) cache.get(username).get();
		}

		R<UserInfoModel> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		if (cache != null) {
			cache.put(username, userDetails);
		}
		return userDetails;
	}

	/**
	 * 构建userdetails
	 * @param result 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(R<UserInfoModel> result) {
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		UserInfoModel info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

		}
		Collection<? extends GrantedAuthority> authorities = BoosterGrantedAuthority
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUserEntity user = info.getSysUser();

		// 构造security用户
		return new BoosterUser(user.getUserId(), user.getDeptId(), user.getUsername(),
				SecurityConstants.BCRYPT + user.getPassword(),
				StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL), true, true, true, authorities);
	}

	@Override
	public UserDetails loadUserBySocial(String inStr) throws UsernameNotFoundException {
		return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
	}

	@Override
	public Boolean lockUser(String username) {
		R<Boolean> result = remoteUserService.lockUser(username, SecurityConstants.FROM_IN);
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return result.getData();
	}
}
