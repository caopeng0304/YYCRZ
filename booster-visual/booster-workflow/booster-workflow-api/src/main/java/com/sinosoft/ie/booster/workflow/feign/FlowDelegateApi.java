package com.sinosoft.ie.booster.workflow.feign;

import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.workflow.entity.FlowDelegateEntity;
import com.sinosoft.ie.booster.workflow.feign.fallback.FlowDelegateApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * api接口
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@FeignClient(contextId = "flowDelegateApi", name = ServiceNameConstants.WORKFLOW_SERVICE, fallback = FlowDelegateApiFallback.class, path = "/Engine/FlowDelegate")
public interface FlowDelegateApi {
	/**
	 * 列表
	 *
	 * @return
	 */
	@GetMapping("/getList")
	List<FlowDelegateEntity> getList();

}
