package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormBatchTableEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formbatchtable.BatchTableForm;
import com.sinosoft.ie.booster.workflow.model.formbatchtable.BatchTableInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormBatchTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 行文呈批表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "行文呈批表", value = "BatchTable")
@RestController
@RequestMapping("/Form/BatchTable")
public class FormBatchTableController {

	@Autowired
	private FormBatchTableService batchTableService;

	/**
	 * 获取行文呈批表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取行文呈批表信息")
	@GetMapping("/{id}")
	public R<BatchTableInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormBatchTableEntity entity = batchTableService.getInfo(id);
		BatchTableInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, BatchTableInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建行文呈批表
	 *
	 * @param batchTableForm 表单对象
	 * @return
	 */
	@ApiOperation("新建行文呈批表")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid BatchTableForm batchTableForm) throws WorkFlowException {
		if (batchTableForm.getShareNum() != null && StrUtil.isNotEmpty(batchTableForm.getShareNum()) && !RegexUtils.checkDigit2(batchTableForm.getShareNum())) {
			return R.failed("份数只能输入正整数");
		}
		FormBatchTableEntity entity = JsonUtil.getJsonToBean(batchTableForm, FormBatchTableEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(batchTableForm.getStatus())) {
			batchTableService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		batchTableService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改行文呈批表
	 *
	 * @param batchTableForm 表单对象
	 * @param id             主键
	 * @return
	 */
	@ApiOperation("修改行文呈批表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid BatchTableForm batchTableForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (batchTableForm.getShareNum() != null && StrUtil.isNotEmpty(batchTableForm.getShareNum()) && !RegexUtils.checkDigit2(batchTableForm.getShareNum())) {
			return R.failed("份数只能输入正整数");
		}
		FormBatchTableEntity entity = JsonUtil.getJsonToBean(batchTableForm, FormBatchTableEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(batchTableForm.getStatus())) {
			batchTableService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		batchTableService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
