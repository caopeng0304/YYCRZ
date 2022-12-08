package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormReceiptProcessingEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formreceiptprocessing.ReceiptProcessingForm;
import com.sinosoft.ie.booster.workflow.model.formreceiptprocessing.ReceiptProcessingInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormReceiptProcessingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 收文处理表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "收文处理表", value = "ReceiptProcessing")
@RestController
@RequestMapping("/Form/ReceiptProcessing")
public class FormReceiptProcessingController {

	@Autowired
	private FormReceiptProcessingService receiptProcessingService;

	/**
	 * 获取收文处理表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取收文处理表信息")
	@GetMapping("/{id}")
	public R<ReceiptProcessingInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormReceiptProcessingEntity entity = receiptProcessingService.getInfo(id);
		ReceiptProcessingInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ReceiptProcessingInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建收文处理表
	 *
	 * @param receiptProcessingForm 表单对象
	 * @return
	 */
	@ApiOperation("新建收文处理表")
	@PostMapping
	public R<Boolean> create(@RequestBody ReceiptProcessingForm receiptProcessingForm) throws WorkFlowException {
		FormReceiptProcessingEntity entity = JsonUtil.getJsonToBean(receiptProcessingForm, FormReceiptProcessingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(receiptProcessingForm.getStatus())) {
			receiptProcessingService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		receiptProcessingService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改收文处理表
	 *
	 * @param receiptProcessingForm 表单对象
	 * @param id                    主键
	 * @return
	 */
	@ApiOperation("修改收文处理表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody ReceiptProcessingForm receiptProcessingForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormReceiptProcessingEntity entity = JsonUtil.getJsonToBean(receiptProcessingForm, FormReceiptProcessingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(receiptProcessingForm.getStatus())) {
			receiptProcessingService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		receiptProcessingService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
