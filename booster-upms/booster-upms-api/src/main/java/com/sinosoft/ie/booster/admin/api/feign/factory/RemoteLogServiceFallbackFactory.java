package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemoteLogService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemoteLogServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Component
public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

	@Override
	public RemoteLogService create(Throwable throwable) {
		RemoteLogServiceFallbackImpl remoteLogServiceFallback = new RemoteLogServiceFallbackImpl();
		remoteLogServiceFallback.setCause(throwable);
		return remoteLogServiceFallback;
	}

}
