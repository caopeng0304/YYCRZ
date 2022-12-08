package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormDocumentSigningEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formdocumentsigning.DocumentSigningForm;
import com.sinosoft.ie.booster.workflow.model.formdocumentsigning.DocumentSigningInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormDocumentSigningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 文件签阅表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "文件签阅表", value = "DocumentSigning")
@RestController
@RequestMapping("/Form/DocumentSigning")
public class FormDocumentSigningController {

	@Autowired
	private FormDocumentSigningService documentSigningService;

	/**
	 * 获取文件签阅表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取文件签阅表信息")
	@GetMapping("/{id}")
	public R<DocumentSigningInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormDocumentSigningEntity entity = documentSigningService.getInfo(id);
		DocumentSigningInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DocumentSigningInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建文件签阅表
	 *
	 * @param documentSigningForm 表单对象
	 * @return
	 */
	@ApiOperation("新建文件签阅表")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid DocumentSigningForm documentSigningForm) throws WorkFlowException {
		FormDocumentSigningEntity entity = JsonUtil.getJsonToBean(documentSigningForm, FormDocumentSigningEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(documentSigningForm.getStatus())) {
			documentSigningService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		documentSigningService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改文件签阅表
	 *
	 * @param documentSigningForm 表单对象
	 * @param id                  主键
	 * @return
	 */
	@ApiOperation("修改文件签阅表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid DocumentSigningForm documentSigningForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormDocumentSigningEntity entity = JsonUtil.getJsonToBean(documentSigningForm, FormDocumentSigningEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(documentSigningForm.getStatus())) {
			documentSigningService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		documentSigningService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
