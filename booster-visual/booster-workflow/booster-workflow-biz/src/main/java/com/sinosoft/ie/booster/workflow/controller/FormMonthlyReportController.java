package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormMonthlyReportEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formmonthlyreport.MonthlyReportForm;
import com.sinosoft.ie.booster.workflow.model.formmonthlyreport.MonthlyReportInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormMonthlyReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 月工作总结
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "月工作总结", value = "MonthlyReport")
@RestController
@RequestMapping("/Form/MonthlyReport")
public class FormMonthlyReportController {

	@Autowired
	private FormMonthlyReportService monthlyReportService;

	/**
	 * 获取月工作总结信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取月工作总结信息")
	@GetMapping("/{id}")
	public R<MonthlyReportInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormMonthlyReportEntity entity = monthlyReportService.getInfo(id);
		MonthlyReportInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, MonthlyReportInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建月工作总结
	 *
	 * @param monthlyReportForm 表单对象
	 * @return
	 */
	@ApiOperation("新建月工作总结")
	@PostMapping
	public R<Boolean> create(@RequestBody MonthlyReportForm monthlyReportForm) throws WorkFlowException {
		FormMonthlyReportEntity entity = JsonUtil.getJsonToBean(monthlyReportForm, FormMonthlyReportEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(monthlyReportForm.getStatus())) {
			monthlyReportService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		monthlyReportService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改月工作总结
	 *
	 * @param monthlyReportForm 表单对象
	 * @param id                主键
	 * @return
	 */
	@ApiOperation("修改月工作总结")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody MonthlyReportForm monthlyReportForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormMonthlyReportEntity entity = JsonUtil.getJsonToBean(monthlyReportForm, FormMonthlyReportEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(monthlyReportForm.getStatus())) {
			monthlyReportService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		monthlyReportService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
