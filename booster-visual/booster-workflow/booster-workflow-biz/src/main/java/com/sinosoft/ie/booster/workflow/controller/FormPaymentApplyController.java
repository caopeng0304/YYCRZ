package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormPaymentApplyEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formpaymentapply.PaymentApplyForm;
import com.sinosoft.ie.booster.workflow.model.formpaymentapply.PaymentApplyInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormPaymentApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 付款申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "付款申请单", value = "PaymentApply")
@RestController
@RequestMapping("/Form/PaymentApply")
public class FormPaymentApplyController {

	@Autowired
	private FormPaymentApplyService paymentApplyService;

	/**
	 * 获取付款申请单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取付款申请单信息")
	@GetMapping("/{id}")
	public R<PaymentApplyInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormPaymentApplyEntity entity = paymentApplyService.getInfo(id);
		PaymentApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PaymentApplyInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建付款申请单
	 *
	 * @param paymentApplyForm 表单对象
	 * @return
	 */
	@ApiOperation("新建付款申请单")
	@PostMapping
	public R<Boolean> create(@RequestBody PaymentApplyForm paymentApplyForm) throws WorkFlowException {
		FormPaymentApplyEntity entity = JsonUtil.getJsonToBean(paymentApplyForm, FormPaymentApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(paymentApplyForm.getStatus())) {
			paymentApplyService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		paymentApplyService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改付款申请单
	 *
	 * @param paymentApplyForm 表单对象
	 * @param id               主键
	 * @return
	 */
	@ApiOperation("修改付款申请单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody PaymentApplyForm paymentApplyForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormPaymentApplyEntity entity = JsonUtil.getJsonToBean(paymentApplyForm, FormPaymentApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(paymentApplyForm.getStatus())) {
			paymentApplyService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		paymentApplyService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
