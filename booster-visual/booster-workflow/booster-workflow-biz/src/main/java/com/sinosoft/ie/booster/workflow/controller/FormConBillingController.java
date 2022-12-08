package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormConBillingEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formconbilling.ConBillingForm;
import com.sinosoft.ie.booster.workflow.model.formconbilling.ConBillingInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormConBillingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 合同开票流程
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "合同开票流程", value = "ConBilling")
@RestController
@RequestMapping("/Form/ConBilling")
public class FormConBillingController {

	@Autowired
	private FormConBillingService conBillingService;

	/**
	 * 获取合同开票流程信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取合同开票流程信息")
	@GetMapping("/{id}")
	public R<ConBillingInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormConBillingEntity entity = conBillingService.getInfo(id);
		ConBillingInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ConBillingInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建合同开票流程
	 *
	 * @param conBillingForm 表单对象
	 * @return
	 */
	@ApiOperation("新建合同开票流程")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ConBillingForm conBillingForm) throws WorkFlowException {
		if (conBillingForm.getBillAmount() != null && !"".equals(String.valueOf(conBillingForm.getBillAmount())) && !RegexUtils.checkDecimals2(String.valueOf(conBillingForm.getBillAmount()))) {
			return R.failed("开票金额必须大于0，最多可以精确到小数点后两位");
		}
		if (conBillingForm.getPayAmount() != null && !"".equals(String.valueOf(conBillingForm.getPayAmount())) && !RegexUtils.checkDecimals2(String.valueOf(conBillingForm.getPayAmount()))) {
			return R.failed("付款金额必须大于0，最多可以精确到小数点后两位");
		}
		FormConBillingEntity entity = JsonUtil.getJsonToBean(conBillingForm, FormConBillingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(conBillingForm.getStatus())) {
			conBillingService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		conBillingService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改合同开票流程
	 *
	 * @param conBillingForm 表单对象
	 * @param id             主键
	 * @return
	 */
	@ApiOperation("修改合同开票流程")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ConBillingForm conBillingForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (conBillingForm.getBillAmount() != null && !RegexUtils.checkDecimals2(String.valueOf(conBillingForm.getBillAmount()))) {
			return R.failed("开票金额必须大于0，最多可以精确到小数点后两位");
		}
		if (conBillingForm.getPayAmount() != null && !RegexUtils.checkDecimals2(String.valueOf(conBillingForm.getPayAmount()))) {
			return R.failed("付款金额必须大于0，最多可以精确到小数点后两位");
		}
		FormConBillingEntity entity = JsonUtil.getJsonToBean(conBillingForm, FormConBillingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(conBillingForm.getStatus())) {
			conBillingService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		conBillingService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
