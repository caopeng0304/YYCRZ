package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormBatchPackEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formbatchpack.BatchPackForm;
import com.sinosoft.ie.booster.workflow.model.formbatchpack.BatchPackInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormBatchPackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 批包装指令
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "批包装指令", value = "BatchPack")
@RestController
@RequestMapping("/Form/BatchPack")
public class FormBatchPackController {

	@Autowired
	private FormBatchPackService batchPackService;

	/**
	 * 获取批包装指令信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取批包装指令信息")
	@GetMapping("/{id}")
	public R<BatchPackInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormBatchPackEntity entity = batchPackService.getInfo(id);
		BatchPackInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, BatchPackInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建批包装指令
	 *
	 * @param batchPackForm 表单对象
	 * @return
	 */
	@ApiOperation("新建批包装指令")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid BatchPackForm batchPackForm) throws WorkFlowException {
		if (batchPackForm.getProductionQuty() != null && StrUtil.isNotEmpty(batchPackForm.getProductionQuty()) && !RegexUtils.checkDigit2(batchPackForm.getProductionQuty())) {
			return R.failed("批产数量只能输入正整数");
		}
		FormBatchPackEntity entity = JsonUtil.getJsonToBean(batchPackForm, FormBatchPackEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(batchPackForm.getStatus())) {
			batchPackService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		batchPackService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改批包装指令
	 *
	 * @param batchPackForm 表单对象
	 * @param id            主键
	 * @return
	 */
	@ApiOperation("修改批包装指令")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid BatchPackForm batchPackForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (batchPackForm.getProductionQuty() != null && StrUtil.isNotEmpty(batchPackForm.getProductionQuty()) && !RegexUtils.checkDigit2(batchPackForm.getProductionQuty())) {
			return R.failed("批产数量只能输入正整数");
		}
		FormBatchPackEntity entity = JsonUtil.getJsonToBean(batchPackForm, FormBatchPackEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(batchPackForm.getStatus())) {
			batchPackService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		batchPackService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
