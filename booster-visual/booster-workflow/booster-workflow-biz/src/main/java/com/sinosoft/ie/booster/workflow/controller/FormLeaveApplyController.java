package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormLeaveApplyEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formleaveapply.LeaveApplyForm;
import com.sinosoft.ie.booster.workflow.model.formleaveapply.LeaveApplyInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormLeaveApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 流程表单【请假申请】
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "请假申请", value = "LeaveApply")
@RestController
@RequestMapping("/Form/LeaveApply")
public class FormLeaveApplyController {

	@Autowired
	private FormLeaveApplyService leaveApplyService;

	/**
	 * 获取请假申请信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取请假申请信息")
	@GetMapping("/{id}")
	public R<LeaveApplyInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormLeaveApplyEntity entity = leaveApplyService.getInfo(id);
		LeaveApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, LeaveApplyInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建请假申请
	 *
	 * @param leaveApplyForm 表单对象
	 * @return
	 */
	@ApiOperation("新建请假申请")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid LeaveApplyForm leaveApplyForm) throws WorkFlowException {
		if (leaveApplyForm.getLeaveStartTime() > leaveApplyForm.getLeaveEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveDayCount())) {
			return R.failed("请假天数只能是0.5的倍数");
		}
		if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveHour())) {
			return R.failed("请假小时只能是0.5的倍数");
		}
		FormLeaveApplyEntity entity = JsonUtil.getJsonToBean(leaveApplyForm, FormLeaveApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(leaveApplyForm.getStatus())) {
			leaveApplyService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		leaveApplyService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改请假申请
	 *
	 * @param leaveApplyForm 表单对象
	 * @param id             主键
	 * @return
	 */
	@ApiOperation("修改请假申请")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid LeaveApplyForm leaveApplyForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (leaveApplyForm.getLeaveStartTime() > leaveApplyForm.getLeaveEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveDayCount())) {
			return R.failed("请假天数只能是0.5的倍数");
		}
		if (!RegexUtils.checkLeave(leaveApplyForm.getLeaveHour())) {
			return R.failed("请假小时只能是0.5的倍数");
		}
		FormLeaveApplyEntity entity = JsonUtil.getJsonToBean(leaveApplyForm, FormLeaveApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(leaveApplyForm.getStatus())) {
			leaveApplyService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		leaveApplyService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
