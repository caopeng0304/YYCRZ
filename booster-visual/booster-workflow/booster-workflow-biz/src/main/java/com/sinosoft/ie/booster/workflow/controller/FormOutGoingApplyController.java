package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormOutGoingApplyEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formoutgoingapply.OutgoingApplyForm;
import com.sinosoft.ie.booster.workflow.model.formoutgoingapply.OutgoingApplyInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormOutGoingApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 外出申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "外出申请单", value = "OutgoingApply")
@RestController
@RequestMapping("/Form/OutgoingApply")
public class FormOutGoingApplyController {

	@Autowired
	private FormOutGoingApplyService outgoingApplyService;

	/**
	 * 获取外出申请单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取外出申请单信息")
	@GetMapping("/{id}")
	public R<OutgoingApplyInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormOutGoingApplyEntity entity = outgoingApplyService.getInfo(id);
		OutgoingApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, OutgoingApplyInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建外出申请单
	 *
	 * @param outgoingApplyForm 表单对象
	 * @return
	 */
	@ApiOperation("新建外出申请单")
	@PostMapping
	public R<Boolean> create(@RequestBody OutgoingApplyForm outgoingApplyForm) throws WorkFlowException {
		if (outgoingApplyForm.getStartTime() > outgoingApplyForm.getEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormOutGoingApplyEntity entity = JsonUtil.getJsonToBean(outgoingApplyForm, FormOutGoingApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(outgoingApplyForm.getStatus())) {
			outgoingApplyService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		outgoingApplyService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改外出申请单
	 *
	 * @param outgoingApplyForm 表单对象
	 * @param id                主键
	 * @return
	 */
	@ApiOperation("修改外出申请单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody OutgoingApplyForm outgoingApplyForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (outgoingApplyForm.getStartTime() > outgoingApplyForm.getEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormOutGoingApplyEntity entity = JsonUtil.getJsonToBean(outgoingApplyForm, FormOutGoingApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(outgoingApplyForm.getStatus())) {
			outgoingApplyService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		outgoingApplyService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
