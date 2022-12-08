package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.model.flowmonitor.FlowMonitorListVO;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowDeleteModel;
import com.sinosoft.ie.booster.workflow.model.flowtask.PaginationFlowTask;
import com.sinosoft.ie.booster.workflow.service.FlowEngineService;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 流程监控
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "流程监控", value = "FlowMonitor")
@RestController
@RequestMapping("/Engine/FlowMonitor")
public class FlowMonitorController {

	@Autowired
	private FlowEngineService flowEngineService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private RemoteUserService usersApi;

	/**
	 * 获取流程监控列表
	 *
	 * @param paginationFlowTask
	 * @return
	 */
	@ApiOperation("获取流程监控列表")
	@GetMapping
	public R<PageListVO<FlowMonitorListVO>> list(PaginationFlowTask paginationFlowTask) {
		List<FlowEngineEntity> engineList = flowEngineService.getList();
		List<FlowTaskEntity> list = flowTaskService.getMonitorList(paginationFlowTask);
		List<FlowMonitorListVO> listVO = new LinkedList<>();
		for (FlowTaskEntity taskEntity : list) {
			//用户名称赋值
			FlowMonitorListVO vo = JsonUtil.getJsonToBean(taskEntity, FlowMonitorListVO.class);
			UserVO user = usersApi.getUserByUserName(taskEntity.getCreateBy()).getData();
			if (user != null) {
				vo.setUserName(user.getUsername());
			} else {
				vo.setUserName("");
			}
			FlowEngineEntity engine = engineList.stream().filter(t -> t.getId().equals(taskEntity.getFlowId())).findFirst().orElse(null);
			if (engine != null) {
				vo.setFormData(engine.getFormData());
				vo.setFormType(engine.getFormType());
				listVO.add(vo);
			}
		}
		PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationFlowTask, PaginationVO.class);
		PageListVO<FlowMonitorListVO> vo = new PageListVO<>();
		vo.setList(listVO);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 批量删除流程监控
	 *
	 * @param deleteModel 主键
	 * @return
	 */
	@ApiOperation("批量删除流程监控")
	@DeleteMapping
	public R<Boolean> delete(@RequestBody FlowDeleteModel deleteModel) {
		String[] taskId = deleteModel.getIds().split(",");
		flowTaskService.delete(taskId);
		return R.ok(null, "删除成功");
	}

}
