package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormStaffOvertimeEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formstaffovertime.StaffOvertimeForm;
import com.sinosoft.ie.booster.workflow.model.formstaffovertime.StaffOvertimeInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormStaffOvertimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 员工加班申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "员工加班申请表", value = "StaffOvertime")
@RestController
@RequestMapping("/Form/StaffOvertime")
public class FormStaffOvertimeController {

	@Autowired
	private FormStaffOvertimeService staffOvertimeService;

	/**
	 * 获取员工加班申请表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取员工加班申请表信息")
	@GetMapping("/{id}")
	public R<StaffOvertimeInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormStaffOvertimeEntity entity = staffOvertimeService.getInfo(id);
		StaffOvertimeInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, StaffOvertimeInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建员工加班申请表
	 *
	 * @param staffOvertimeForm 表单对象
	 * @return
	 */
	@ApiOperation("新建员工加班申请表")
	@PostMapping
	public R<Boolean> create(@RequestBody StaffOvertimeForm staffOvertimeForm) throws WorkFlowException {
		if (staffOvertimeForm.getStartTime() > staffOvertimeForm.getEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormStaffOvertimeEntity entity = JsonUtil.getJsonToBean(staffOvertimeForm, FormStaffOvertimeEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(staffOvertimeForm.getStatus())) {
			staffOvertimeService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		staffOvertimeService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改
	 *
	 * @param staffOvertimeForm 表单对象
	 * @param id                主键
	 * @return
	 */
	@ApiOperation("修改员工加班申请表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody StaffOvertimeForm staffOvertimeForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (staffOvertimeForm.getStartTime() > staffOvertimeForm.getEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormStaffOvertimeEntity entity = JsonUtil.getJsonToBean(staffOvertimeForm, FormStaffOvertimeEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(staffOvertimeForm.getStatus())) {
			staffOvertimeService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		staffOvertimeService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
