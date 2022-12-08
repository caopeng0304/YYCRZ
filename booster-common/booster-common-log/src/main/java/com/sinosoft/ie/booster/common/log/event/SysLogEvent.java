package com.sinosoft.ie.booster.common.log.event;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import org.springframework.context.ApplicationEvent;

/**
 * @author lengleng 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLogEntity source) {
		super(source);
	}

}
