package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemoteRegionService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemoteRegionServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author haocy
 * @since 2021-11-09
 */
@Component
public class RemoteRegionServiceFallbackFactory implements FallbackFactory<RemoteRegionService> {

	@Override
	public RemoteRegionService create(Throwable throwable) {
		RemoteRegionServiceFallbackImpl remoteRegionServiceFallback = new RemoteRegionServiceFallbackImpl();
		remoteRegionServiceFallback.setCause(throwable);
		return remoteRegionServiceFallback;
	}

}
