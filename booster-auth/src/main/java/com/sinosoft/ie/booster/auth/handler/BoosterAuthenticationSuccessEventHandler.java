package com.sinosoft.ie.booster.auth.handler;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.log.event.SysLogEvent;
import com.sinosoft.ie.booster.common.log.util.SysLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Slf4j
@Component
public class BoosterAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
		SysLogEntity logVo = SysLogUtils.getSysLog();
		logVo.setTitle("登录成功");
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		logVo.setCreateBy(authentication.getName());
		logVo.setUpdateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
