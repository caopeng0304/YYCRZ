package com.sinosoft.ie.booster.workflow.feign.fallback;

import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.feign.FlowTaskApi;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSaveForm;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSubmitForm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * api接口
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@Component
public class FlowTaskApiFallback implements FlowTaskApi {
	@Override
	public List<FlowTaskEntity> getWaitList() {
		return null;
	}

	@Override
	public List<FlowTaskEntity> getTrialList() {
		return null;
	}

	@Override
	public List<FlowTaskEntity> getAllWaitList() {
		return null;
	}

	@Override
	public Boolean saveFlowTask(FlowTaskSaveForm flowTaskSaveForm) {
		return Boolean.FALSE;
	}

	@Override
	public Boolean submitFlowTask(FlowTaskSubmitForm flowTaskSubmitForm) {
		return Boolean.FALSE;
	}
}
