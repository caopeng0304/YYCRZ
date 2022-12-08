package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormReceiptSignEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formreceiptsign.ReceiptSignForm;
import com.sinosoft.ie.booster.workflow.model.formreceiptsign.ReceiptSignInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormReceiptSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 收文签呈单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "收文签呈单", value = "ReceiptSign")
@RestController
@RequestMapping("/Form/ReceiptSign")
public class FormReceiptSignController {

	@Autowired
	private FormReceiptSignService receiptSignService;

	/**
	 * 获取收文签呈单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取收文签呈单信息")
	@GetMapping("/{id}")
	public R<ReceiptSignInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormReceiptSignEntity entity = receiptSignService.getInfo(id);
		ReceiptSignInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ReceiptSignInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建收文签呈单
	 *
	 * @param receiptSignForm 表单对象
	 * @return
	 */
	@ApiOperation("新建收文签呈单")
	@PostMapping
	public R<Boolean> create(@RequestBody ReceiptSignForm receiptSignForm) throws WorkFlowException {
		FormReceiptSignEntity entity = JsonUtil.getJsonToBean(receiptSignForm, FormReceiptSignEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(receiptSignForm.getStatus())) {
			receiptSignService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		receiptSignService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改收文签呈单
	 *
	 * @param receiptSignForm 表单对象
	 * @param id              主键
	 * @return
	 */
	@ApiOperation("修改收文签呈单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody ReceiptSignForm receiptSignForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormReceiptSignEntity entity = JsonUtil.getJsonToBean(receiptSignForm, FormReceiptSignEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(receiptSignForm.getStatus())) {
			receiptSignService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		receiptSignService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
