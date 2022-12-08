package com.sinosoft.ie.booster.auth.provider;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author lengleng
 * @since 2018/8/15
 */
public interface BoosterUserDetailsService extends UserDetailsService {

	/**
	 * 根据社交登录code 登录
	 *
	 * @param code TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserBySocial(String code) throws UsernameNotFoundException;

	/**
	 * 锁定用户
	 * @param username 用户名
	 * @return 操作状态
	 */
	Boolean lockUser(String username);
}
