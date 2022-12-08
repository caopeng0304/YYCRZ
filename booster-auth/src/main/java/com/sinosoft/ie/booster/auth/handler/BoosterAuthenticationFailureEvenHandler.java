package com.sinosoft.ie.booster.auth.handler;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.log.event.SysLogEvent;
import com.sinosoft.ie.booster.common.log.util.LogTypeEnum;
import com.sinosoft.ie.booster.common.log.util.SysLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Slf4j
@Component
public class BoosterAuthenticationFailureEvenHandler extends AbstractAuthenticationFailureEvenHandler {

	/**
	 * 处理登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 */
	@Override
	public void handle(AuthenticationException authenticationException, Authentication authentication) {
		log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
		SysLogEntity logVo = SysLogUtils.getSysLog();
		logVo.setTitle("登录失败");
		logVo.setType(LogTypeEnum.ERROR.getType());
		logVo.setException(authenticationException.getMessage());
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		logVo.setCreateBy(authentication.getName());
		logVo.setUpdateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
