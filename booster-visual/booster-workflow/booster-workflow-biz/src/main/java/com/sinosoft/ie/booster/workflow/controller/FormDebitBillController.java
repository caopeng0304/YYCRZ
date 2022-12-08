package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormDebitBillEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formdebitbill.DebitBillForm;
import com.sinosoft.ie.booster.workflow.model.formdebitbill.DebitBillInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormDebitBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 借支单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "借支单", value = "DebitBill")
@RestController
@RequestMapping("/Form/DebitBill")
public class FormDebitBillController {

	@Autowired
	private FormDebitBillService debitBillService;

	/**
	 * 获取借支单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取借支单信息")
	@GetMapping("/{id}")
	public R<DebitBillInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormDebitBillEntity entity = debitBillService.getInfo(id);
		DebitBillInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DebitBillInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建借支单
	 *
	 * @param debitBillForm 表单对象
	 * @return
	 */
	@ApiOperation("新建借支单")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid DebitBillForm debitBillForm) throws WorkFlowException {
		if (debitBillForm.getAmountDebit() != null && !"".equals(String.valueOf(debitBillForm.getAmountDebit())) && !RegexUtils.checkDecimals2(String.valueOf(debitBillForm.getAmountDebit()))) {
			return R.failed("借支金额必须大于0，最多可以输入两位小数点");
		}
		FormDebitBillEntity entity = JsonUtil.getJsonToBean(debitBillForm, FormDebitBillEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(debitBillForm.getStatus())) {
			debitBillService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		debitBillService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改借支单
	 *
	 * @param debitBillForm 表单对象
	 * @param id            主键
	 * @return
	 */
	@ApiOperation("修改借支单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid DebitBillForm debitBillForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (debitBillForm.getAmountDebit() != null && !"".equals(String.valueOf(debitBillForm.getAmountDebit())) && !RegexUtils.checkDecimals2(String.valueOf(debitBillForm.getAmountDebit()))) {
			return R.failed("借支金额必须大于0，最多可以输入两位小数点");
		}
		FormDebitBillEntity entity = JsonUtil.getJsonToBean(debitBillForm, FormDebitBillEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(debitBillForm.getStatus())) {
			debitBillService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		debitBillService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
