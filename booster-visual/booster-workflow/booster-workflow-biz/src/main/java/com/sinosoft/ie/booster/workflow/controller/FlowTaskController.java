package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.convert.Convert;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskForm;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskInfoVO;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSaveForm;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSubmitForm;
import com.sinosoft.ie.booster.workflow.service.FlowDynamicService;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 流程任务
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "流程任务", value = "FlowTask")
@RestController
@RequestMapping("/Engine/FlowTask")
public class FlowTaskController {

	@Autowired
	private FlowDynamicService flowDynamicService;
	@Autowired
	private FlowTaskService flowTaskService;

	/**
	 * 动态表单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("动态表单信息")
	@GetMapping("/{id}")
	public R<FlowTaskInfoVO> info(@PathVariable("id") Long id) throws DataException, WorkFlowException, SQLException {
		FlowTaskEntity entity = flowTaskService.getInfo(id);
		FlowTaskInfoVO vo = flowDynamicService.info(entity);
		return R.ok(vo);
	}

	/**
	 * 保存
	 *
	 * @param flowTaskForm 动态表单
	 * @return
	 */
	@ApiOperation("保存")
	@PostMapping
	public R<Boolean> save(@RequestBody FlowTaskForm flowTaskForm) throws WorkFlowException, DataException, SQLException {
		if (FlowStatusEnum.save.getMessage().equals(flowTaskForm.getStatus())) {
			flowDynamicService.save(null, Convert.toLong(flowTaskForm.getFlowId()), flowTaskForm.getData());
			return R.ok(null, "保存成功");
		}
		flowDynamicService.submit(null, Convert.toLong(flowTaskForm.getFlowId()), flowTaskForm.getData(), flowTaskForm.getFreeApprover());
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 提交
	 *
	 * @param flowTaskForm 动态表单
	 * @return
	 */
	@ApiOperation("提交")
	@PutMapping("/{id}")
	public R<Boolean> submit(@RequestBody FlowTaskForm flowTaskForm, @PathVariable("id") Long id) throws WorkFlowException, DataException, SQLException {
		if (FlowStatusEnum.save.getMessage().equals(flowTaskForm.getStatus())) {
			flowDynamicService.save(id, Convert.toLong(flowTaskForm.getFlowId()), flowTaskForm.getData());
			return R.ok(null, "保存成功");
		}
		flowDynamicService.submit(id, Convert.toLong(flowTaskForm.getFlowId()), flowTaskForm.getData(), flowTaskForm.getFreeApprover());
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 动态表单详情
	 *
	 * @param flowId 引擎主键值
	 * @param id     主键值
	 * @return
	 */
	@ApiOperation("动态表单信息")
	@GetMapping("/{flowId}/{id}")
	public R<Map<String, Object>> info(@PathVariable("flowId") Long flowId, @PathVariable("id") Long id) throws DataException, WorkFlowException, SQLException {
		Map<String, Object> data = flowDynamicService.getData(flowId, id);
		return R.ok(data);
	}

	//———————————————内部使用接口——————————

	/**
	 * 列表（待我审批）
	 *
	 * @return
	 */
	@GetMapping("/GetWaitList")
	public List<FlowTaskEntity> getWaitList() {
		return flowTaskService.getWaitList();
	}

	/**
	 * 列表（我已审批）
	 *
	 * @return
	 */
	@GetMapping("/GetTrialList")
	public List<FlowTaskEntity> getTrialList() {
		return flowTaskService.getTrialList();
	}

	/**
	 * 列表（待我审批）
	 *
	 * @return
	 */
	@GetMapping("/GetAllWaitList")
	public List<FlowTaskEntity> getAllWaitList() {
		return flowTaskService.getAllWaitList();
	}

	/**
	 * 保存流程任务
	 *
	 * @param flowTaskSaveForm 保存草稿任务表单
	 * @return 是否成功
	 */
	@PostMapping("/SaveFlowTask")
	public Boolean saveFlowTask(@RequestBody FlowTaskSaveForm flowTaskSaveForm) {
		try {
			flowTaskService.save(Convert.toLong(flowTaskSaveForm.getId()), Convert.toLong(flowTaskSaveForm.getFlowId()),
					Convert.toLong(flowTaskSaveForm.getProcessId()), flowTaskSaveForm.getFlowTitle(),
					flowTaskSaveForm.getFlowUrgent(), flowTaskSaveForm.getBillNo());
			return Boolean.TRUE;
		} catch (WorkFlowException ex) {
			return Boolean.FALSE;
		}
	}

	/**
	 * 提交流程任务
	 *
	 * @param flowTaskSubmitForm 提交任务表单
	 * @return 是否成功
	 */
	@PostMapping("/SubmitFlowTask")
	public Boolean submitFlowTask(@RequestBody FlowTaskSubmitForm flowTaskSubmitForm) {
		try {
			flowTaskService.submit(Convert.toLong(flowTaskSubmitForm.getId()), Convert.toLong(flowTaskSubmitForm.getFlowId()),
					Convert.toLong(flowTaskSubmitForm.getProcessId()), flowTaskSubmitForm.getFlowTitle(),
					flowTaskSubmitForm.getFlowUrgent(), flowTaskSubmitForm.getBillNo(),
					flowTaskSubmitForm.getFormEntity(), flowTaskSubmitForm.getFreeApprover());
			return Boolean.TRUE;
		} catch (WorkFlowException ex) {
			return Boolean.FALSE;
		}
	}

}
