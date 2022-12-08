package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormSalesSupportEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formsalessupport.SalesSupportForm;
import com.sinosoft.ie.booster.workflow.model.formsalessupport.SalesSupportInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormSalesSupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 销售支持表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "销售支持表", value = "SalesSupport")
@RestController
@RequestMapping("/Form/SalesSupport")
public class FormSalesSupportController {

	@Autowired
	private FormSalesSupportService salesSupportService;

	/**
	 * 获取销售支持表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取销售支持表信息")
	@GetMapping("/{id}")
	public R<SalesSupportInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormSalesSupportEntity entity = salesSupportService.getInfo(id);
		SalesSupportInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, SalesSupportInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建销售支持表
	 *
	 * @param salesSupportForm 表单对象
	 * @return
	 */
	@ApiOperation("新建保存销售支持表")
	@PostMapping
	public R<Boolean> create(@RequestBody SalesSupportForm salesSupportForm) throws WorkFlowException {
		FormSalesSupportEntity entity = JsonUtil.getJsonToBean(salesSupportForm, FormSalesSupportEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(salesSupportForm.getStatus())) {
			salesSupportService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		salesSupportService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改销售支持表
	 *
	 * @param salesSupportForm 表单对象
	 * @param id               主键
	 * @return
	 */
	@ApiOperation("修改销售支持表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody SalesSupportForm salesSupportForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormSalesSupportEntity entity = JsonUtil.getJsonToBean(salesSupportForm, FormSalesSupportEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(salesSupportForm.getStatus())) {
			salesSupportService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		salesSupportService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
