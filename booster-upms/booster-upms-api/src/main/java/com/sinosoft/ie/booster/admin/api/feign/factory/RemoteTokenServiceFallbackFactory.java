package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemoteTokenService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemoteTokenServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

	@Override
	public RemoteTokenService create(Throwable throwable) {
		RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
		remoteTokenServiceFallback.setCause(throwable);
		return remoteTokenServiceFallback;
	}

}
