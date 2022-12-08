package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormContractApprovalSheetEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formcontractapprovalsheet.ContractApprovalSheetForm;
import com.sinosoft.ie.booster.workflow.model.formcontractapprovalsheet.ContractApprovalSheetInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormContractApprovalSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 合同申请单表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "合同申请单表", value = "ContractApprovalSheet")
@RestController
@RequestMapping("/Form/ContractApprovalSheet")
public class FormContractApprovalSheetController {

	@Autowired
	private FormContractApprovalSheetService contractApprovalSheetService;

	/**
	 * 获取合同申请单表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取合同申请单表信息")
	@GetMapping("/{id}")
	public R<ContractApprovalSheetInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormContractApprovalSheetEntity entity = contractApprovalSheetService.getInfo(id);
		ContractApprovalSheetInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ContractApprovalSheetInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建合同申请单表
	 *
	 * @param contractApprovalSheetForm 表单对象
	 * @return
	 */
	@ApiOperation("新建合同申请单表")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ContractApprovalSheetForm contractApprovalSheetForm) throws WorkFlowException {
		if (contractApprovalSheetForm.getStartContractDate() > contractApprovalSheetForm.getEndContractDate()) {
			return R.failed("结束时间不能小于开始时间");
		}
		if (contractApprovalSheetForm.getIncomeAmount() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getIncomeAmount())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getIncomeAmount()))) {
			return R.failed("收入金额必须大于0，最多可以输入两位小数点");
		}
		if (contractApprovalSheetForm.getTotalExpenditure() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getIncomeAmount())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getTotalExpenditure()))) {
			return R.failed("支出金额必须大于0，最多可以输入两位小数点");
		}
		FormContractApprovalSheetEntity entity = JsonUtil.getJsonToBean(contractApprovalSheetForm, FormContractApprovalSheetEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(contractApprovalSheetForm.getStatus())) {
			contractApprovalSheetService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		contractApprovalSheetService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改合同申请单表
	 *
	 * @param contractApprovalSheetForm 表单对象
	 * @param id                        主键
	 * @return
	 */
	@ApiOperation("修改合同申请单表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ContractApprovalSheetForm contractApprovalSheetForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (contractApprovalSheetForm.getStartContractDate() > contractApprovalSheetForm.getEndContractDate()) {
			return R.failed("结束时间不能小于开始时间");
		}
		if (contractApprovalSheetForm.getIncomeAmount() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getIncomeAmount())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getIncomeAmount()))) {
			return R.failed("收入金额必须大于0，最多可以输入两位小数点");
		}
		if (contractApprovalSheetForm.getTotalExpenditure() != null && !"".equals(String.valueOf(contractApprovalSheetForm.getTotalExpenditure())) && !RegexUtils.checkDecimals2(String.valueOf(contractApprovalSheetForm.getTotalExpenditure()))) {
			return R.failed("支出金额必须大于0，最多可以输入两位小数点");
		}
		FormContractApprovalSheetEntity entity = JsonUtil.getJsonToBean(contractApprovalSheetForm, FormContractApprovalSheetEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(contractApprovalSheetForm.getStatus())) {
			contractApprovalSheetService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		contractApprovalSheetService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
