package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormQuotationApprovalEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formquotationapproval.QuotationApprovalForm;
import com.sinosoft.ie.booster.workflow.model.formquotationapproval.QuotationApprovalInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormQuotationApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 报价审批表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "报价审批表", value = "QuotationApproval")
@RestController
@RequestMapping("/Form/QuotationApproval")
public class FormQuotationApprovalController {

	@Autowired
	private FormQuotationApprovalService quotationApprovalService;

	/**
	 * 获取报价审批表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取报价审批表信息")
	@GetMapping("/{id}")
	public R<QuotationApprovalInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormQuotationApprovalEntity entity = quotationApprovalService.getInfo(id);
		QuotationApprovalInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, QuotationApprovalInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建报价审批表
	 *
	 * @param quotationApprovalForm 表单对象
	 * @return
	 */
	@ApiOperation("新建报价审批表")
	@PostMapping
	public R<Boolean> create(@RequestBody QuotationApprovalForm quotationApprovalForm) throws WorkFlowException {
		FormQuotationApprovalEntity entity = JsonUtil.getJsonToBean(quotationApprovalForm, FormQuotationApprovalEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(quotationApprovalForm.getStatus())) {
			quotationApprovalService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		quotationApprovalService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改报价审批表
	 *
	 * @param quotationApprovalForm 表单对象
	 * @param id                    主键
	 * @return
	 */
	@ApiOperation("修改报价审批表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody QuotationApprovalForm quotationApprovalForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormQuotationApprovalEntity entity = JsonUtil.getJsonToBean(quotationApprovalForm, FormQuotationApprovalEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(quotationApprovalForm.getStatus())) {
			quotationApprovalService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		quotationApprovalService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
