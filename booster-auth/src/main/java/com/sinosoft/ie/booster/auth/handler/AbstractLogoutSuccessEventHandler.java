package com.sinosoft.ie.booster.auth.handler;

import cn.hutool.core.collection.CollUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * 退出成功事件处理器
 *
 * @author zhangran
 * @since 2021/6/23
 */
public abstract class AbstractLogoutSuccessEventHandler implements ApplicationListener<LogoutSuccessEvent> {

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(LogoutSuccessEvent event) {
		Authentication authentication = (Authentication) event.getSource();
		if (CollUtil.isNotEmpty(authentication.getAuthorities())) {
			handle(authentication);
		}
	}

	/**
	 * 处理退出成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	public abstract void handle(Authentication authentication);

}
