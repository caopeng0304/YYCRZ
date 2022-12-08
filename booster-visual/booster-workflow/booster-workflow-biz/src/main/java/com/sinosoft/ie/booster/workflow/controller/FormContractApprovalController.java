package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormContractApprovalEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formcontractapproval.ContractApprovalForm;
import com.sinosoft.ie.booster.workflow.model.formcontractapproval.ContractApprovalInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormContractApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 合同审批
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "合同审批", value = "ContractApproval")
@RestController
@RequestMapping("/Form/ContractApproval")
public class FormContractApprovalController {

	@Autowired
	private FormContractApprovalService contractApprovalService;

	/**
	 * 获取合同审批信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取合同审批信息")
	@GetMapping("/{id}")
	public R<ContractApprovalInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormContractApprovalEntity entity = contractApprovalService.getInfo(id);
		ContractApprovalInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ContractApprovalInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建合同审批
	 *
	 * @param contractApprovalForm 表单对象
	 * @return
	 */
	@ApiOperation("新建合同审批")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ContractApprovalForm contractApprovalForm) throws WorkFlowException {
		FormContractApprovalEntity entity = JsonUtil.getJsonToBean(contractApprovalForm, FormContractApprovalEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(contractApprovalForm.getStatus())) {
			contractApprovalService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		contractApprovalService.submit(entity.getId(), entity, contractApprovalForm.getFreeApprover());
		return R.ok(null,"提交成功，请耐心等待");
	}

	/**
	 * 修改合同审批
	 *
	 * @param contractApprovalForm 表单对象
	 * @param id                   主键
	 * @return
	 */
	@ApiOperation("修改合同审批")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ContractApprovalForm contractApprovalForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormContractApprovalEntity entity = JsonUtil.getJsonToBean(contractApprovalForm, FormContractApprovalEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(contractApprovalForm.getStatus())) {
			contractApprovalService.save(id, entity);
			return R.ok(null,"保存成功");
		}
		contractApprovalService.submit(id, entity, contractApprovalForm.getFreeApprover());
		return R.ok(null,"提交成功，请耐心等待");
	}
}
