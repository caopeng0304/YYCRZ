package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.feign.factory.RemoteBillRuleServiceFallbackFactory;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author haocy
 * @since 2021-08-19
 */
@FeignClient(contextId = "remoteBillRuleService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteBillRuleServiceFallbackFactory.class)
public interface RemoteBillRuleService {

	/**
	 * 获取单据流水号
	 *
	 * @param enCode 单据编号
	 * @return R
	 */
	@GetMapping("/billrule/getBillNumber/{enCode}")
	R<String> getBillNumber(@PathVariable("enCode") String enCode);

}
