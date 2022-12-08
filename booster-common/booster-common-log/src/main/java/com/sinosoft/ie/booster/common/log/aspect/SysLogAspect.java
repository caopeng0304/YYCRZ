package com.sinosoft.ie.booster.common.log.aspect;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.log.event.SysLogEvent;
import com.sinosoft.ie.booster.common.log.util.LogTypeEnum;
import com.sinosoft.ie.booster.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 操作日志使用spring event异步入库
 *
 * @author L.cm
 */
@Aspect
@Slf4j
public class SysLogAspect {

	@Around("@annotation(sysLog)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, com.sinosoft.ie.booster.common.log.annotation.SysLog sysLog) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		SysLogEntity logVo = SysLogUtils.getSysLog();
		logVo.setTitle(sysLog.value());

		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;

		try {
			obj = point.proceed();
		}
		catch (Exception e) {
			logVo.setType(LogTypeEnum.ERROR.getType());
			logVo.setException(e.getMessage());
			throw e;
		}
		finally {
			Long endTime = System.currentTimeMillis();
			logVo.setTime(endTime - startTime);
			SpringContextHolder.publishEvent(new SysLogEvent(logVo));
		}

		return obj;
	}

}
