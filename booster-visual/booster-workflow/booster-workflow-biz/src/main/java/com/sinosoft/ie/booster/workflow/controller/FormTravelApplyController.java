package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormTravelApplyEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formtravelapply.TravelApplyForm;
import com.sinosoft.ie.booster.workflow.model.formtravelapply.TravelApplyInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormTravelApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 出差预支申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "出差预支申请单", value = "TravelApply")
@RestController
@RequestMapping("/Form/TravelApply")
public class FormTravelApplyController {

	@Autowired
	private FormTravelApplyService travelApplyService;

	/**
	 * 获取出差预支申请单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取出差预支申请单信息")
	@GetMapping("/{id}")
	public R<TravelApplyInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormTravelApplyEntity entity = travelApplyService.getInfo(id);
		TravelApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, TravelApplyInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建出差预支申请单
	 *
	 * @param travelApplyForm 表单对象
	 * @return
	 */
	@ApiOperation("新建出差预支申请单")
	@PostMapping
	public R<Boolean> create(@RequestBody TravelApplyForm travelApplyForm) throws WorkFlowException {
		FormTravelApplyEntity entity = JsonUtil.getJsonToBean(travelApplyForm, FormTravelApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(travelApplyForm.getStatus())) {
			travelApplyService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		travelApplyService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改出差预支申请单
	 *
	 * @param travelApplyForm 表单对象
	 * @param id              主键
	 * @return
	 */
	@ApiOperation("修改出差预支申请单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody TravelApplyForm travelApplyForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormTravelApplyEntity entity = JsonUtil.getJsonToBean(travelApplyForm, FormTravelApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(travelApplyForm.getStatus())) {
			travelApplyService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		travelApplyService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
