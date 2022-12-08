package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.admin.api.feign.NoticeApi;
import com.sinosoft.ie.booster.admin.api.feign.EmailApi;
import com.sinosoft.ie.booster.visualdev.model.portal.*;
import com.sinosoft.ie.booster.workflow.feign.FlowDelegateApi;
import com.sinosoft.ie.booster.workflow.feign.FlowTaskApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 主页控制器
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "主页控制器")
@RestController
@RequestMapping("/Dashboard")
public class DashboardController {
	@Autowired
	private FlowTaskApi flowTaskApi;

	@Autowired
	private FlowDelegateApi flowDelegateApi;

	@Autowired
	private NoticeApi noticeApi;

	@Autowired
	private EmailApi emailApi;

	/**
	 * 获取我的待办
	 *
	 * @return
	 */
	@ApiOperation("获取我的待办")
	@GetMapping("/FlowTodoCount")
	public R<FlowTodoCountVO> getFlowTodoCount() {
		FlowTodoCountVO vo = new FlowTodoCountVO();
		vo.setToBeReviewed(flowTaskApi.getWaitList().size());
		vo.setEntrust(flowDelegateApi.getList().size());
		vo.setFlowDone(flowTaskApi.getTrialList().size());
		return R.ok(vo);
	}

	/**
	 * 获取通知公告
	 *
	 * @return
	 */
	@ApiOperation("获取通知公告")
	@GetMapping("/Notice")
	public R<ListVO<NoticeVO>> getNotice() {
		List<NoticeVO> list = JsonUtil.getJsonToList(noticeApi.getNoticeList(), NoticeVO.class);
		ListVO<NoticeVO> voList = new ListVO<>();
		voList.setList(list);
		return R.ok(voList);
	}

	/**
	 * 获取未读邮件
	 *
	 * @return
	 */
	@ApiOperation("获取未读邮件")
	@GetMapping("/Email")
	public R<ListVO<EmailVO>> getEmail() {
		List<EmailVO> list = JsonUtil.getJsonToList(emailApi.getReceiveList(), EmailVO.class);
		ListVO<EmailVO> voList = new ListVO<>();
		voList.setList(list);
		return R.ok(voList);
	}

	/**
	 * 获取待办事项
	 *
	 * @return
	 */
	@ApiOperation("获取待办事项")
	@GetMapping("/FlowTodo")
	public R<ListVO<FlowTodoVO>> getFlowTodo() {
		List<FlowTodoVO> list = JsonUtil.getJsonToList(flowTaskApi.getAllWaitList(), FlowTodoVO.class);
		ListVO<FlowTodoVO> voList = new ListVO<>();
		voList.setList(list);
		return R.ok(voList);
	}

	/**
	 * 获取我的待办事项
	 *
	 * @return
	 */
	@ApiOperation("获取我的待办事项")
	@GetMapping("/MyFlowTodo")
	public R<ListVO<MyFlowTodoVO>> getMyFlowTodo() {
		List<MyFlowTodoVO> list = JsonUtil.getJsonToList(flowTaskApi.getWaitList(), MyFlowTodoVO.class);
		ListVO<MyFlowTodoVO> voList = new ListVO<>();
		voList.setList(list);
		return R.ok(voList);
	}
}
