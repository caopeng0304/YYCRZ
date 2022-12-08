package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.ExtEmailReceiveEntity;
import com.sinosoft.ie.booster.admin.api.feign.fallback.EmailApiFallback;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(contextId = "emailApi", name = ServiceNameConstants.UMPS_SERVICE, fallback = EmailApiFallback.class, path = "/Email")
public interface EmailApi {
	/**
	 * 列表（收件箱）
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/GetReceiveList")
	List<ExtEmailReceiveEntity> getReceiveList();

}
