package com.sinosoft.ie.booster.auth.handler;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.log.event.SysLogEvent;
import com.sinosoft.ie.booster.common.log.util.SysLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @author zhangran
 * @since 2021/6/23
 */
@Slf4j
@Component
public class BoosterLogoutSuccessEventHandler extends AbstractLogoutSuccessEventHandler {

	/**
	 * 处理退出成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 退出成功", authentication.getPrincipal());
		SysLogEntity logVo = SysLogUtils.getSysLog();
		logVo.setTitle("退出成功");
		OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
		logVo.setParams(authenticationDetails == null ? null : authenticationDetails.getTokenValue());
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		// 这边设置ServiceId
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			logVo.setServiceId(auth2Authentication.getOAuth2Request().getClientId());
		}
		logVo.setCreateBy(authentication.getName());
		logVo.setUpdateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
