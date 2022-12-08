package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemotePositionServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author haocy
 * @since 2022-09-16
 */
@Component
public class RemotePositionServiceFallbackFactory implements FallbackFactory<RemotePositionService> {

	@Override
	public RemotePositionService create(Throwable throwable) {
		RemotePositionServiceFallbackImpl remotePositionServiceFallback = new RemotePositionServiceFallbackImpl();
		remotePositionServiceFallback.setCause(throwable);
		return remotePositionServiceFallback;
	}
}
