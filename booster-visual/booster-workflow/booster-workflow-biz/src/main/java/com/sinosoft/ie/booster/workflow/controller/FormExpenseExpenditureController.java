package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormExpenseExpenditureEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formexpenseexpenditure.ExpenseExpenditureForm;
import com.sinosoft.ie.booster.workflow.model.formexpenseexpenditure.ExpenseExpenditureInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormExpenseExpenditureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 费用支出单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "费用支出单", value = "ExpenseExpenditure")
@RestController
@RequestMapping("/Form/ExpenseExpenditure")
public class FormExpenseExpenditureController {

	@Autowired
	private FormExpenseExpenditureService expenseExpenditureService;

	/**
	 * 获取费用支出单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取费用支出单信息")
	@GetMapping("/{id}")
	public R<ExpenseExpenditureInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormExpenseExpenditureEntity entity = expenseExpenditureService.getInfo(id);
		ExpenseExpenditureInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ExpenseExpenditureInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建费用支出单
	 *
	 * @param expenseExpenditureForm 表单对象
	 * @return
	 */
	@ApiOperation("新建费用支出单")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ExpenseExpenditureForm expenseExpenditureForm) throws WorkFlowException {
		FormExpenseExpenditureEntity entity = JsonUtil.getJsonToBean(expenseExpenditureForm, FormExpenseExpenditureEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(expenseExpenditureForm.getStatus())) {
			expenseExpenditureService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		expenseExpenditureService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改费用支出单
	 *
	 * @param expenseExpenditureForm 表单对象
	 * @param id                     主键
	 * @return
	 */
	@ApiOperation("修改费用支出单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ExpenseExpenditureForm expenseExpenditureForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormExpenseExpenditureEntity entity = JsonUtil.getJsonToBean(expenseExpenditureForm, FormExpenseExpenditureEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(expenseExpenditureForm.getStatus())) {
			expenseExpenditureService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		expenseExpenditureService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
