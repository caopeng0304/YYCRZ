package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemoteDictServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author haocy
 * @since 2021-09-15
 */
@Component
public class RemoteDictServiceFallbackFactory implements FallbackFactory<RemoteDictService> {

	@Override
	public RemoteDictService create(Throwable throwable) {
		RemoteDictServiceFallbackImpl remoteDictServiceFallback = new RemoteDictServiceFallbackImpl();
		remoteDictServiceFallback.setCause(throwable);
		return remoteDictServiceFallback;
	}

}
