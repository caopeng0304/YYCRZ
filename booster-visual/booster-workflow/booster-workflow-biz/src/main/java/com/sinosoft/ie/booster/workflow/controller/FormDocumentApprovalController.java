package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormDocumentApprovalEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formdocumentapproval.DocumentApprovalForm;
import com.sinosoft.ie.booster.workflow.model.formdocumentapproval.DocumentApprovalInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormDocumentApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 文件签批意见表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "文件签批意见表", value = "DocumentApproval")
@RestController
@RequestMapping("/Form/DocumentApproval")
public class FormDocumentApprovalController {

	@Autowired
	private FormDocumentApprovalService documentApprovalService;

	/**
	 * 获取文件签批意见表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取文件签批意见表信息")
	@GetMapping("/{id}")
	public R<DocumentApprovalInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormDocumentApprovalEntity entity = documentApprovalService.getInfo(id);
		DocumentApprovalInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DocumentApprovalInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建文件签批意见表
	 *
	 * @param documentApprovalForm 表单对象
	 * @return
	 */
	@ApiOperation("新建文件签批意见表")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid DocumentApprovalForm documentApprovalForm) throws WorkFlowException {
		FormDocumentApprovalEntity entity = JsonUtil.getJsonToBean(documentApprovalForm, FormDocumentApprovalEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(documentApprovalForm.getStatus())) {
			documentApprovalService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		documentApprovalService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改文件签批意见表
	 *
	 * @param documentApprovalForm 表单对象
	 * @param id                   主键
	 * @return
	 */
	@ApiOperation("修改文件签批意见表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid DocumentApprovalForm documentApprovalForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormDocumentApprovalEntity entity = JsonUtil.getJsonToBean(documentApprovalForm, FormDocumentApprovalEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(documentApprovalForm.getStatus())) {
			documentApprovalService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		documentApprovalService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
