package com.sinosoft.ie.booster.workflow.feign.fallback;

import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.workflow.feign.FlowEngineApi;
import com.sinosoft.ie.booster.workflow.model.flowengine.FlowEngineInfoVO;
import com.sinosoft.ie.booster.workflow.model.flowengine.FlowEngineListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * api接口
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@Component
@Slf4j
public class FlowEngineApiFallback implements FlowEngineApi {
	@Override
	public R<ListVO<FlowEngineListVO>> listAll() {
		log.error(ServiceNameConstants.WORKFLOW_SERVICE + "服务未启动");
		return null;
	}

	@Override
	public R<FlowEngineInfoVO> getInfo(String id) {
		return null;
	}
}
