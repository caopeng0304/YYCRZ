package com.sinosoft.ie.booster.workflow.feign;

import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.workflow.feign.fallback.FlowEngineApiFallback;
import com.sinosoft.ie.booster.workflow.model.flowengine.FlowEngineInfoVO;
import com.sinosoft.ie.booster.workflow.model.flowengine.FlowEngineListVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * api接口
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@FeignClient(contextId = "flowEngineApi", name = ServiceNameConstants.WORKFLOW_SERVICE, fallback = FlowEngineApiFallback.class, path = "/Engine/FlowEngine")
public interface FlowEngineApi {
	/**
	 * 列表
	 *
	 * @return
	 */
	@GetMapping("/ListAll")
	R<ListVO<FlowEngineListVO>> listAll();

	/**
	 * 获取流程引擎信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@GetMapping("/{id}")
	R<FlowEngineInfoVO> getInfo(@PathVariable("id") String id);

}
