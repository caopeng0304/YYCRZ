package com.sinosoft.ie.booster.workflow.feign;

import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.feign.fallback.FlowTaskApiFallback;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSaveForm;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSubmitForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * api接口
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@FeignClient(contextId = "flowTaskApi", name = ServiceNameConstants.WORKFLOW_SERVICE, fallback = FlowTaskApiFallback.class, path = "/Engine/FlowTask")
public interface FlowTaskApi {
	/**
	 * 列表（待我审批）
	 *
	 * @return
	 */
	@GetMapping("/GetWaitList")
	List<FlowTaskEntity> getWaitList();

	/**
	 * 列表（我已审批）
	 *
	 * @return
	 */
	@GetMapping("/GetTrialList")
	List<FlowTaskEntity> getTrialList();

	/**
	 * 列表（待我审批）
	 *
	 * @return
	 */
	@GetMapping("/GetAllWaitList")
	List<FlowTaskEntity> getAllWaitList();

	/**
	 * 保存流程任务
	 *
	 * @param flowTaskSaveForm 保存草稿任务表单
	 * @return 是否成功
	 */
	@PostMapping("/SaveFlowTask")
	Boolean saveFlowTask(@RequestBody FlowTaskSaveForm flowTaskSaveForm);

	/**
	 * 提交流程任务
	 *
	 * @param flowTaskSubmitForm 提交任务表单
	 * @return 是否成功
	 */
	@PostMapping("/SubmitFlowTask")
	Boolean submitFlowTask(@RequestBody FlowTaskSubmitForm flowTaskSubmitForm);

}
