package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormIncomeRecognitionEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formincomerecognition.IncomeRecognitionForm;
import com.sinosoft.ie.booster.workflow.model.formincomerecognition.IncomeRecognitionInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormIncomeRecognitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 收入确认分析表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "收入确认分析表", value = "IncomeRecognition")
@RestController
@RequestMapping("/Form/IncomeRecognition")
public class FormIncomeRecognitionController {

	@Autowired
	private FormIncomeRecognitionService incomeRecognitionService;

	/**
	 * 获取收入确认分析表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取收入确认分析表信息")
	@GetMapping("/{id}")
	public R<IncomeRecognitionInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormIncomeRecognitionEntity entity = incomeRecognitionService.getInfo(id);
		IncomeRecognitionInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, IncomeRecognitionInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建收入确认分析表
	 *
	 * @param incomeRecognitionForm 表单对象
	 * @return
	 */
	@ApiOperation("新建收入确认分析表")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid IncomeRecognitionForm incomeRecognitionForm) throws WorkFlowException {
		FormIncomeRecognitionEntity entity = JsonUtil.getJsonToBean(incomeRecognitionForm, FormIncomeRecognitionEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(incomeRecognitionForm.getStatus())) {
			incomeRecognitionService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		incomeRecognitionService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改收入确认分析表
	 *
	 * @param incomeRecognitionForm 表单对象
	 * @param id                    主键
	 * @return
	 */
	@ApiOperation("修改收入确认分析表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid IncomeRecognitionForm incomeRecognitionForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormIncomeRecognitionEntity entity = JsonUtil.getJsonToBean(incomeRecognitionForm, FormIncomeRecognitionEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(incomeRecognitionForm.getStatus())) {
			incomeRecognitionService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		incomeRecognitionService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
