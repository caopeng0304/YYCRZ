package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormApplyMeetingEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formapplymeeting.ApplyMeetingForm;
import com.sinosoft.ie.booster.workflow.model.formapplymeeting.ApplyMeetingInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormApplyMeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 会议申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "会议申请", value = "ApplyMeeting")
@RestController
@RequestMapping("/Form/ApplyMeeting")
public class FormApplyMeetingController {

	@Autowired
	private FormApplyMeetingService applyMeetingService;

	/**
	 * 获取会议申请信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取会议申请信息")
	@GetMapping("/{id}")
	public R<ApplyMeetingInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormApplyMeetingEntity entity = applyMeetingService.getInfo(id);
		ApplyMeetingInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ApplyMeetingInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建会议申请
	 *
	 * @param applyMeetingForm 表单对象
	 * @return
	 */
	@ApiOperation("新建会议申请")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ApplyMeetingForm applyMeetingForm) throws WorkFlowException {
		if (applyMeetingForm.getStartDate() > applyMeetingForm.getEndDate()) {
			return R.failed("结束时间不能小于开始时间");
		}
		if (applyMeetingForm.getEstimatePeople() != null && StrUtil.isNotEmpty(applyMeetingForm.getEstimatePeople()) && !RegexUtils.checkDigit2(applyMeetingForm.getEstimatePeople())) {
			return R.failed("预计人数只能输入正整数");
		}
		if (applyMeetingForm.getEstimatedAmount() != null && !RegexUtils.checkDecimals2(String.valueOf(applyMeetingForm.getEstimatedAmount()))) {
			return R.failed("预计金额必须大于0，最多精确小数点后两位");
		}
		FormApplyMeetingEntity entity = JsonUtil.getJsonToBean(applyMeetingForm, FormApplyMeetingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(applyMeetingForm.getStatus())) {
			applyMeetingService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		applyMeetingService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改会议申请
	 *
	 * @param applyMeetingForm 表单对象
	 * @param id               主键
	 * @return
	 */
	@ApiOperation("修改会议申请")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ApplyMeetingForm applyMeetingForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (applyMeetingForm.getStartDate() > applyMeetingForm.getEndDate()) {
			return R.failed("结束时间不能小于开始时间");
		}
		if (applyMeetingForm.getEstimatePeople() != null && StrUtil.isNotEmpty(applyMeetingForm.getEstimatePeople()) && !RegexUtils.checkDigit2(applyMeetingForm.getEstimatePeople())) {
			return R.failed("预计人数只能输入正整数");
		}
		if (applyMeetingForm.getEstimatedAmount() != null && !RegexUtils.checkDecimals2(String.valueOf(applyMeetingForm.getEstimatedAmount()))) {
			return R.failed("预计金额必须大于0，最多精确小数点后两位");
		}
		FormApplyMeetingEntity entity = JsonUtil.getJsonToBean(applyMeetingForm, FormApplyMeetingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(applyMeetingForm.getStatus())) {
			applyMeetingService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		applyMeetingService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
