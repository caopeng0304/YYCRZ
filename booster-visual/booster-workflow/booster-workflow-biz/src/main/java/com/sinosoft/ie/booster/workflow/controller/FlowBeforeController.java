package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.workflow.entity.*;
import com.sinosoft.ie.booster.workflow.enums.FlowBeforeEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowNodeEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowTaskOperatorEnum;
import com.sinosoft.ie.booster.workflow.model.FlowHandleModel;
import com.sinosoft.ie.booster.workflow.model.flowbefore.*;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.FormOperates;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.Properties;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.ChildNodeList;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.DateProperties;
import com.sinosoft.ie.booster.workflow.model.flowtask.PaginationFlowTask;
import com.sinosoft.ie.booster.workflow.service.*;
import com.sinosoft.ie.booster.workflow.util.FlowJsonUtil;
import com.sinosoft.ie.booster.workflow.util.FlowNature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 待我审核
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "待我审核", value = "FlowBefore")
@RestController
@RequestMapping("/Engine/FlowBefore")
public class FlowBeforeController {

	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FlowTaskOperatorService flowTaskOperatorService;
	@Autowired
	private FlowTaskOperatorRecordService flowTaskOperatorRecordService;
	@Autowired
	private FlowTaskNodeService flowTaskNodeService;
	@Autowired
	private FlowEngineService flowEngineService;
	@Autowired
	private RemoteUserService usersApi;
	@Autowired
	private RemotePositionService positionApi;
	@Autowired
	private RemoteDeptService organizeApi;

	/**
	 * 获取待我审核列表
	 *
	 * @param category           分类
	 * @param paginationFlowTask
	 * @return
	 */
	@ApiOperation("获取待我审核列表(有带分页)，1-待办事宜，2-已办事宜，3-抄送事宜")
	@GetMapping("/List/{category}")
	public R<PageListVO<FlowBeforeListVO>> list(@PathVariable("category") String category, PaginationFlowTask paginationFlowTask) {
		List<FlowTaskEntity> data = new ArrayList<>();
		if (FlowNature.WAIT.equals(category)) {
			data = flowTaskService.getWaitList(paginationFlowTask);
		} else if (FlowNature.TRIAL.equals(category)) {
			data = flowTaskService.getTrialList(paginationFlowTask);
		} else if (FlowNature.CIRCULATE.equals(category)) {
			data = flowTaskService.getCirculateList(paginationFlowTask);
		}
		List<FlowBeforeListVO> listVO = new LinkedList<>();
		if (data.size() > 0) {
			List<FlowEngineEntity> engineList = flowEngineService.getList();
			for (FlowTaskEntity taskEntity : data) {
				//用户名称赋值
				FlowBeforeListVO vo = JsonUtil.getJsonToBean(taskEntity, FlowBeforeListVO.class);
				FlowEngineEntity engine = engineList.stream().filter(t -> t.getId().equals(taskEntity.getFlowId())).findFirst().orElse(null);
				if (engine != null) {
					vo.setFormType(engine.getFormType());
				}
				listVO.add(vo);
			}
		}
		PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationFlowTask, PaginationVO.class);
		PageListVO<FlowBeforeListVO> vo = new PageListVO<>();
		vo.setList(listVO);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 获取待我审批信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取待我审批信息")
	@GetMapping("/{id}")
	public R<FlowBeforeInfoVO> info(@PathVariable("id") Long id) throws WorkFlowException {
		FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(id);
		List<FlowTaskNodeEntity> flowTaskNodeList = flowTaskNodeService.getList(flowTaskEntity.getId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState()) || FlowNodeEnum.Futility.getCode().equals(t.getState())).collect(Collectors.toList());
		Long freeApprover = flowEngineService.getFlowNodeList(flowTaskEntity.getThisStepId(), flowTaskNodeList);
		//流程经办
		List<FlowTaskOperatorEntity> flowTaskOperatorList = flowTaskOperatorService.getList(flowTaskEntity.getId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
		//审核记录
		List<FlowTaskOperatorRecordEntity> flowTaskOperatorRecordList = flowTaskOperatorRecordService.getList(flowTaskEntity.getId());
		FlowBeforeInfoVO vo = new FlowBeforeInfoVO();
		vo.setFreeApprover(freeApprover);
		//当前节点
		String[] tepId = flowTaskEntity.getThisStepId().split(",");
		List<Integer> collect = flowTaskNodeList.stream().filter(t -> flowTaskEntity.getThisStepId().contains(t.getNodeCode())).map(t -> t.getSort()).distinct().collect(Collectors.toList());
		Set<String> upAll = new HashSet<>();
		Set<String> nextAll = new HashSet<>();
		for (String node : tepId) {
			//当前完成节点
			Set<String> upList = new HashSet<>();
			FlowJsonUtil.upList(flowTaskNodeList, node, upList, tepId);
			upAll.addAll(upList);
			//当前之后节点
			Set<String> nextList = new HashSet<>();
			FlowJsonUtil.nextList(flowTaskNodeList, node, nextList, tepId);
			nextAll.addAll(nextList);
		}
		//替换当前节点
		Set<String> thisTepId = new HashSet<>();
		Set<String> thisTepName = new HashSet<>();
		List<FlowTaskNodeEntityInfoModel> flowTaskNodes = JsonUtil.getJsonToList(flowTaskNodeList, FlowTaskNodeEntityInfoModel.class);
		List<FlowTaskNodeEntityInfoModel> flowTaskNodesAll = new ArrayList<>();
		for (FlowTaskNodeEntityInfoModel modelList : flowTaskNodes) {
			ChildNodeList models = JsonUtil.getJsonToBean(modelList.getNodePropertyJson(), ChildNodeList.class);
			Properties properties = models.getProperties();
			String assType = String.valueOf(properties.getAssigneeType());
			String code = modelList.getNodeCode();
			//用户名称赋值
			List<String> userList = new ArrayList<>();
			List<String> assignList = new ArrayList<>();
			String thisType = FlowBeforeEnum.Futility.getCode();
			if (upAll.contains(code)) {
				thisType = FlowBeforeEnum.Pass.getCode();
			} else if (nextAll.contains(code)) {
				thisType = FlowBeforeEnum.Undone.getCode();
			} else if (Arrays.asList(tepId).contains(code)) {
				thisType = FlowBeforeEnum.Present.getCode();
			}
			//赋值名称
			if ("start".equals(modelList.getNodeType())) {
				userList.add(flowTaskEntity.getCreateBy());
			} else if (!"endround".equals(modelList.getNodeType())) {
				UserVO userVO = usersApi.getUserByUserName(flowTaskEntity.getCreateBy()).getData();
				if (assType.equals(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()))) {
					//发起者【发起主管】
					String[] managerIdAll = userVO.getManager().split(",");
					for (String managerId : managerIdAll) {
						UserVO manager = usersApi.getUserByUserName(managerId).getData();
						if (manager != null) {
							userList.add(manager.getUsername());
						}
					}
					assignList.add(FlowTaskOperatorEnum.LaunchCharge.getMessage());
				} else if (assType.equals(String.valueOf(FlowTaskOperatorEnum.DepartmentCharge.getCode()))) {
					//发起者【部门主管】
					SysDeptEntity info = organizeApi.infoByName(userVO.getDeptName()).getData();
					UserVO depUserModel = usersApi.getUserByUserName(info.getManager()).getData();
					if (depUserModel != null) {
						userList.add(depUserModel.getUsername());
					}
					assignList.add(FlowTaskOperatorEnum.DepartmentCharge.getMessage());
				} else if (assType.equals(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()))) {
					//发起者【发起本人】
					userList.add(userVO.getUsername());
					assignList.add(FlowTaskOperatorEnum.InitiatorMe.getMessage());
				} else if (assType.equals(String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()))) {
					//发起者【授权审批人】
					List<FlowTaskOperatorEntity> freeAppList = flowTaskOperatorList.stream().filter(t -> t.getNodeCode().equals(models.getCustom().getNodeId())).collect(Collectors.toList());
					if (freeAppList.size() > 0) {
						FlowTaskOperatorEntity flowTask = freeAppList.get(0);
						UserVO freeUser = usersApi.getUserByUserName(flowTask.getHandleId()).getData();
						userList.add(freeUser.getUsername());
					} else {
						userList.add("加签");
					}
					assignList.add(FlowTaskOperatorEnum.FreeApprover.getMessage());
				} else {
					if (properties.getApproverPos() != null) {
						if (properties.getApproverPos().length > 0) {
							List<String> userNames = positionApi.getUserPositionByIds(String.join(",", properties.getApproverPos())).getData().getUserNames();
							userList.addAll(userNames);
						}
					}
					if (properties.getApprovers() != null) {
						for (String approvers : properties.getApprovers()) {
							if (userList.stream().noneMatch(t -> t.equals(approvers))) {
								userList.add(approvers);
							}
						}
					}
					if (StrUtil.isNotEmpty(assType)) {
						assignList.add(FlowTaskOperatorEnum.getMessageByCode(assType));
					}
				}
			}
			//判断当前节点
			if (FlowBeforeEnum.Present.getCode().equals(thisType)) {
				List<DateProperties> timerAll = models.getTimerAll();
				Date date = new Date();
				//获取定时器的list
				DateProperties dateProperties = timerAll.stream().filter(t -> t.getDate().getTime() > date.getTime()).findFirst().orElse(null);
				String thisStepId = modelList.getNodeCode();
				String thisStepName = modelList.getNodeName();
				//判断定时器是否还没有结束
				if (collect.size() == 1 && dateProperties != null) {
					thisStepId = dateProperties.getNodeId();
					thisStepName = dateProperties.getTitle();
					thisType = FlowBeforeEnum.Undone.getCode();
					//添加定时器节点
					FlowTaskNodeEntityInfoModel timer = new FlowTaskNodeEntityInfoModel();
					timer.setType(FlowBeforeEnum.Present.getCode());
					timer.setNodeCode(thisStepId);
					flowTaskNodesAll.add(timer);
				}
				thisTepId.add(thisStepId);
				thisTepName.add(thisStepName);
			}
			//赋值节点的类型和名称
			modelList.setType(thisType);
			modelList.setAssigneeName(String.join(",", assignList));
			modelList.setUserName(String.join(",", userList));
			flowTaskNodesAll.add(modelList);
		}
		FlowTaskEntityInfoModel info = JsonUtil.getJsonToBean(flowTaskEntity, FlowTaskEntityInfoModel.class);
		info.setThisStepId(String.join(",", thisTepId));
		info.setThisStep(String.join(",", thisTepName));
		vo.setFlowTaskInfo(info);
		vo.setFlowTaskNodeList(flowTaskNodesAll);
		List<FlowTaskOperatorEntityInfoModel> flowTaskOperator = JsonUtil.getJsonToList(flowTaskOperatorList, FlowTaskOperatorEntityInfoModel.class);
		vo.setFlowTaskOperatorList(flowTaskOperator);
		List<FlowTaskOperatorRecordEntityInfoModel> recordList = JsonUtil.getJsonToList(flowTaskOperatorRecordList, FlowTaskOperatorRecordEntityInfoModel.class);
		if (recordList.size() > 0) {
			for (FlowTaskOperatorRecordEntityInfoModel model : recordList) {
				UserVO user = usersApi.getUserByUserName(model.getHandleId()).getData();
				if (user != null) {
					model.setUserName(user.getUsername());
				}
			}
		}
		//表单数据(要传参数判断点击哪个节点)
		String nodeJson = flowTaskNodes.stream().filter(t -> t.getNodeCode().equals(info.getThisStepId())).findFirst().orElse(new FlowTaskNodeEntityInfoModel()).getNodePropertyJson();
		if (!StrUtil.isEmpty(nodeJson)) {
			ChildNodeList childNodeList = JsonUtil.getJsonToBean(nodeJson, ChildNodeList.class);
			List<FormOperates> formOperates = childNodeList.getProperties().getFormOperates();
			vo.setFormOperates(formOperates);
		}
		vo.setFlowTaskOperatorRecordList(recordList);
		vo.setFlowFormInfo(info.getFlowForm());
		return R.ok(vo);
	}

	/**
	 * 待我审核审核
	 *
	 * @param id              待办主键值
	 * @param flowHandleModel 流程经办
	 * @return
	 */
	@ApiOperation("待我审核审核")
	@PostMapping("/Audit/{id}")
	public R<Boolean> audit(@PathVariable("id") Long id, @RequestBody FlowHandleModel flowHandleModel) throws WorkFlowException {
		FlowTaskOperatorEntity flowTaskOperatorEntity = flowTaskOperatorService.getInfo(id);
		if (flowTaskOperatorEntity == null) {
			return R.failed("审核失败");
		} else {
			FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(flowTaskOperatorEntity.getTaskId());
			if (flowTaskOperatorEntity.getCompletion() == 0) {
				flowTaskService.audit(flowTaskEntity, flowTaskOperatorEntity, flowHandleModel);
				return R.ok(null, "审核成功");
			} else {
				return R.failed("已审核完成");
			}
		}
	}

	/**
	 * 待我审核驳回
	 *
	 * @param id              待办主键值
	 * @param flowHandleModel 经办信息
	 * @return
	 */
	@ApiOperation("待我审核驳回")
	@PostMapping("/Reject/{id}")
	public R<Boolean> reject(@PathVariable("id") Long id, @RequestBody FlowHandleModel flowHandleModel) throws WorkFlowException {
		FlowTaskOperatorEntity flowTaskOperatorEntity = flowTaskOperatorService.getInfo(id);
		if (flowTaskOperatorEntity == null) {
			return R.failed("驳回失败");
		} else {
			FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(flowTaskOperatorEntity.getTaskId());
			if (flowTaskOperatorEntity.getCompletion() == 0) {
				flowTaskService.reject(flowTaskEntity, flowTaskOperatorEntity, flowHandleModel);
				return R.ok(null, "驳回成功");
			} else {
				return R.failed("已审核完成");
			}
		}
	}

	/**
	 * 待我审核转办
	 *
	 * @param id              主键值
	 * @param flowHandleModel 经办信息
	 * @return
	 */
	@ApiOperation("待我审核转办")
	@PostMapping("/Transfer/{id}")
	public R<Boolean> transfer(@PathVariable("id") Long id, @RequestBody FlowHandleModel flowHandleModel) {
		FlowTaskOperatorEntity flowTaskOperatorEntity = flowTaskOperatorService.getInfo(id);
		if (flowTaskOperatorEntity == null) {
			return R.failed("转办失败");
		} else {
			flowTaskOperatorEntity.setHandleId(flowHandleModel.getFreeApprover());
			flowTaskOperatorService.update(flowTaskOperatorEntity);
			return R.ok(null, "转办成功");
		}
	}


	/**
	 * 待我审核撤回审核
	 * 注意：在撤销流程时要保证你的下一节点没有处理这条记录；如已处理则无法撤销流程。
	 *
	 * @param id              主键值
	 * @param flowHandleModel 实体对象
	 * @return
	 */
	@ApiOperation("待我审核撤回审核")
	@PostMapping("/Recall/{id}")
	public R<Boolean> recall(@PathVariable("id") Long id, @RequestBody FlowHandleModel flowHandleModel) throws WorkFlowException {
		FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity = flowTaskOperatorRecordService.getInfo(id);
		FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(flowTaskOperatorRecordEntity.getTaskId());
		List<FlowTaskNodeEntity> flowTaskNodeEntityList = flowTaskNodeService.getList(flowTaskOperatorRecordEntity.getTaskId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
		FlowTaskNodeEntity flowTaskNodeEntity = flowTaskNodeEntityList.stream().filter(t -> t.getId().equals(flowTaskOperatorRecordEntity.getTaskNodeId())).findFirst().orElse(null);
		if (flowTaskNodeEntity != null) {
			String nextId = flowTaskNodeEntity.getNodeNext();
			flowTaskService.recall(flowTaskEntity, flowTaskNodeEntityList, flowTaskOperatorRecordEntity, flowHandleModel);
//            HandleEvent(FlowHandleEventEnum.Recall, flowTaskEntity);
			return R.ok(null, "撤回成功");
		}
		return R.failed("撤回失败");
	}

	/**
	 * 待我审核终止审核
	 *
	 * @param id              主键值
	 * @param flowHandleModel 流程经办
	 * @return
	 */
	@ApiOperation("待我审核终止审核")
	@PostMapping("/Cancel/{id}")
	public R<Boolean> cancel(@PathVariable("id") Long id, @RequestBody FlowHandleModel flowHandleModel) throws WorkFlowException {
		FlowTaskEntity flowTaskEntity = flowTaskService.getInfo(id);
		if (flowTaskEntity != null) {
			flowTaskService.cancel(flowTaskEntity, flowHandleModel);
//            HandleEvent(FlowHandleEventEnum.Cancel, flowTaskEntity);
			return R.ok(null, "终止成功");
		}
		return R.failed("终止失败，数据不存在");
	}

	/**
	 * 流程事件
	 *
	 * @param flowHandleEvent 经办事件
	 * @param flowTaskEntity  流程任务
	 */
//    private void HandleEvent(FlowHandleEventEnum flowHandleEvent, FlowTask flowTaskEntity) {
//        if (flowTaskEntity.getFlowCode().equals(FlowModuleEnum.CRM_Order.getMessage())) {
//            orderService.FlowHandleEvent(flowHandleEvent, flowTaskEntity);
//        }
//    }

}
