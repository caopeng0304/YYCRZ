package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.admin.api.feign.NoticeApi;
import com.sinosoft.ie.booster.admin.api.model.message.SentMessageModel;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskOperatorEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowMessageEnum;
import com.sinosoft.ie.booster.workflow.model.FlowHandleModel;
import com.sinosoft.ie.booster.workflow.model.flowlaunch.FlowLaunchListVO;
import com.sinosoft.ie.booster.workflow.model.flowtask.PaginationFlowTask;
import com.sinosoft.ie.booster.workflow.service.FlowEngineService;
import com.sinosoft.ie.booster.workflow.service.FlowTaskOperatorService;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 流程发起
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "流程发起", value = "FlowLaunch")
@RestController
@RequestMapping("/Engine/FlowLaunch")
public class FlowLaunchController {

	@Autowired
	private FlowEngineService flowEngineService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FlowTaskOperatorService flowTaskOperatorService;
	@Autowired
	private NoticeApi noticeApi;

	/**
	 * 获取流程发起列表
	 *
	 * @param paginationFlowTask
	 * @return
	 */
	@ApiOperation("获取流程发起列表(带分页)")
	@GetMapping
	public R<PageListVO<FlowLaunchListVO>> list(PaginationFlowTask paginationFlowTask) {
		List<FlowEngineEntity> engineList = flowEngineService.getList();
		List<FlowTaskEntity> data = flowTaskService.getLaunchList(paginationFlowTask);
		List<FlowLaunchListVO> listVO = new LinkedList<>();
		for (FlowTaskEntity taskEntity : data) {
			//用户名称赋值
			FlowLaunchListVO vo = JsonUtil.getJsonToBean(taskEntity, FlowLaunchListVO.class);
			FlowEngineEntity entity = engineList.stream().filter(t -> t.getId().equals(taskEntity.getFlowId())).findFirst().orElse(null);
			if (entity != null) {
				vo.setFormData(entity.getFormData());
				vo.setFormType(entity.getFormType());
			}
			listVO.add(vo);
		}
		PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationFlowTask, PaginationVO.class);
		PageListVO<FlowLaunchListVO> vo = new PageListVO<>();
		vo.setList(listVO);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 删除流程发起
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("删除流程发起")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) throws WorkFlowException {
		FlowTaskEntity entity = flowTaskService.getInfo(id);
		if (entity != null) {
			flowTaskService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 待我审核催办
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("发起催办")
	@PostMapping("/Press/{id}")
	public R<Boolean> press(@PathVariable("id") Long id) throws WorkFlowException {
		FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(id);
		//单前流程节点
		String[] stepId = flowTaskEntity.getThisStepId().split(",");
		List<FlowTaskOperatorEntity> press = flowTaskOperatorService.press(stepId, id);
		List<String> processId = press.stream().map(FlowTaskOperatorEntity::getHandleId).distinct().collect(Collectors.toList());
		if (processId.size() > 0) {
			Map<String, Object> message = new HashMap<>(16);
			message.put("type", FlowMessageEnum.wait.getCode());
			message.put("id", processId);
			String bodyText = JsonUtil.getObjectToString(message);
			SentMessageModel model = new SentMessageModel();
			model.setBodyText(bodyText);
			model.setTitle(flowTaskEntity.getFullName() + "【催办】");
			model.setToUserNames(processId);
			noticeApi.sentMessage(model);
			return R.ok(null, "催办成功");
		}
		return R.failed("未找到催办人");
	}

	/**
	 * 撤回流程发起
	 * 注意：在撤销流程时要保证你的下一节点没有处理这条记录；如已处理则无法撤销流程。
	 *
	 * @param id              主键值
	 * @param flowHandleModel 经办记录
	 * @return
	 */
	@ApiOperation("撤回流程发起")
	@PutMapping("/{id}/Actions/Withdraw")
	public R<Boolean> revoke(@PathVariable("id") Long id, @RequestBody FlowHandleModel flowHandleModel) throws WorkFlowException {
		FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(id);
		flowTaskService.revoke(flowTaskEntity, flowHandleModel);
		return R.ok(null, "撤回成功");
	}
}
