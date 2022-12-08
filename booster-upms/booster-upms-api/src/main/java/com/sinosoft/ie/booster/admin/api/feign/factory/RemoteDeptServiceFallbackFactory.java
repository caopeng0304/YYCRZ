package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemoteDeptServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author haocy
 * @since 2021-08-18
 */
@Component
public class RemoteDeptServiceFallbackFactory implements FallbackFactory<RemoteDeptService> {

	@Override
	public RemoteDeptService create(Throwable throwable) {
		RemoteDeptServiceFallbackImpl remoteDeptServiceFallback = new RemoteDeptServiceFallbackImpl();
		remoteDeptServiceFallback.setCause(throwable);
		return remoteDeptServiceFallback;
	}

}
