package com.sinosoft.ie.booster.admin.api.feign.factory;

import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.admin.api.feign.fallback.RemoteBillRuleServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author haocy
 * @since 2021-08-19
 */
@Component
public class RemoteBillRuleServiceFallbackFactory implements FallbackFactory<RemoteBillRuleService> {

	@Override
	public RemoteBillRuleService create(Throwable throwable) {
		RemoteBillRuleServiceFallbackImpl remoteBillRuleServiceFallback = new RemoteBillRuleServiceFallbackImpl();
		remoteBillRuleServiceFallback.setCause(throwable);
		return remoteBillRuleServiceFallback;
	}

}
