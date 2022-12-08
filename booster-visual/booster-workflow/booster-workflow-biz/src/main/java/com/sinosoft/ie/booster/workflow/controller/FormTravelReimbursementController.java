package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormTravelReimbursementEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formtravelreimbursement.TravelReimbursementForm;
import com.sinosoft.ie.booster.workflow.model.formtravelreimbursement.TravelReimbursementInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormTravelReimbursementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 差旅报销申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "差旅报销申请表", value = "TravelReimbursement")
@RestController
@RequestMapping("/Form/TravelReimbursement")
public class FormTravelReimbursementController {

	@Autowired
	private FormTravelReimbursementService travelReimbursementService;

	/**
	 * 获取差旅报销申请表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取差旅报销申请表信息")
	@GetMapping("/{id}")
	public R<TravelReimbursementInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormTravelReimbursementEntity entity = travelReimbursementService.getInfo(id);
		TravelReimbursementInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, TravelReimbursementInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建差旅报销申请表
	 *
	 * @param travelReimbursementForm 表单对象
	 * @return
	 */
	@ApiOperation("新建差旅报销申请表")
	@PostMapping
	public R<Boolean> create(@RequestBody TravelReimbursementForm travelReimbursementForm) throws WorkFlowException {
		if (travelReimbursementForm.getSetOutDate() > travelReimbursementForm.getReturnDate()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormTravelReimbursementEntity entity = JsonUtil.getJsonToBean(travelReimbursementForm, FormTravelReimbursementEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(travelReimbursementForm.getStatus())) {
			travelReimbursementService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		travelReimbursementService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改差旅报销申请表
	 *
	 * @param travelReimbursementForm 表单对象
	 * @param id                      主键
	 * @return
	 */
	@ApiOperation("修改差旅报销申请表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody TravelReimbursementForm travelReimbursementForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (travelReimbursementForm.getSetOutDate() > travelReimbursementForm.getReturnDate()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormTravelReimbursementEntity entity = JsonUtil.getJsonToBean(travelReimbursementForm, FormTravelReimbursementEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(travelReimbursementForm.getStatus())) {
			travelReimbursementService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		travelReimbursementService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
