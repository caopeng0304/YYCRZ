package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormApplyDeliverGoodsEntity;
import com.sinosoft.ie.booster.workflow.entity.FormApplyDeliverGoodsEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formapplydelivergoods.ApplyDeliverGoodsEntryInfoModel;
import com.sinosoft.ie.booster.workflow.model.formapplydelivergoods.ApplyDeliverGoodsForm;
import com.sinosoft.ie.booster.workflow.model.formapplydelivergoods.ApplyDeliverGoodsInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormApplyDeliverGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 发货申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "发货申请单", value = "ApplyDeliverGoods")
@RestController
@RequestMapping("/Form/ApplyDeliverGoods")
public class FormApplyDeliverGoodsController {

	@Autowired
	private FormApplyDeliverGoodsService applyDeliverGoodsService;

	/**
	 * 获取发货申请单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取发货申请单信息")
	@GetMapping("/{id}")
	public R<ApplyDeliverGoodsInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormApplyDeliverGoodsEntity entity = applyDeliverGoodsService.getInfo(id);
		List<FormApplyDeliverGoodsEntryEntity> entityList = applyDeliverGoodsService.getDeliverEntryList(id);
		ApplyDeliverGoodsInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ApplyDeliverGoodsInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, ApplyDeliverGoodsEntryInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建发货申请单
	 *
	 * @param applyDeliverGoodsForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建发货申请单")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ApplyDeliverGoodsForm applyDeliverGoodsForm) throws WorkFlowException {
		FormApplyDeliverGoodsEntity deliver = JsonUtil.getJsonToBean(applyDeliverGoodsForm, FormApplyDeliverGoodsEntity.class);
		List<FormApplyDeliverGoodsEntryEntity> deliverEntryList = JsonUtil.getJsonToList(applyDeliverGoodsForm.getEntryList(), FormApplyDeliverGoodsEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(applyDeliverGoodsForm.getStatus())) {
			applyDeliverGoodsService.save(deliver.getId(), deliver, deliverEntryList);
			return R.ok(null, "保存成功");
		}
		applyDeliverGoodsService.submit(deliver.getId(), deliver, deliverEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改发货申请单
	 *
	 * @param applyDeliverGoodsForm 表单对象
	 * @param id                    主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改发货申请单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ApplyDeliverGoodsForm applyDeliverGoodsForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormApplyDeliverGoodsEntity deliver = JsonUtil.getJsonToBean(applyDeliverGoodsForm, FormApplyDeliverGoodsEntity.class);
		List<FormApplyDeliverGoodsEntryEntity> deliverEntryList = JsonUtil.getJsonToList(applyDeliverGoodsForm.getEntryList(), FormApplyDeliverGoodsEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(applyDeliverGoodsForm.getStatus())) {
			applyDeliverGoodsService.save(id, deliver, deliverEntryList);
			return R.ok(null, "保存成功");
		}
		applyDeliverGoodsService.submit(id, deliver, deliverEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
