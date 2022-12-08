package com.sinosoft.ie.booster.common.log.event;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteLogService;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author lengleng 异步监听日志事件
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

	private final RemoteLogService remoteLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLogEntity sysLog = (SysLogEntity) event.getSource();
		remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
	}

}
