package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormSalesOrderEntity;
import com.sinosoft.ie.booster.workflow.entity.FormSalesOrderEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formsalesorder.SalesOrderEntryEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formsalesorder.SalesOrderForm;
import com.sinosoft.ie.booster.workflow.model.formsalesorder.SalesOrderInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormSalesOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 销售订单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "销售订单", value = "SalesOrder")
@RestController
@RequestMapping("/Form/SalesOrder")
public class FormSalesOrderController {

	@Autowired
	private FormSalesOrderService salesOrderService;

	/**
	 * 获取销售订单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取销售订单信息")
	@GetMapping("/{id}")
	public R<SalesOrderInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormSalesOrderEntity entity = salesOrderService.getInfo(id);
		List<FormSalesOrderEntryEntity> entityList = salesOrderService.getSalesEntryList(id);
		SalesOrderInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, SalesOrderInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, SalesOrderEntryEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建销售订单
	 *
	 * @param salesOrderForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建销售订单")
	@PostMapping
	public R<Boolean> create(@RequestBody SalesOrderForm salesOrderForm) throws WorkFlowException {
		FormSalesOrderEntity sales = JsonUtil.getJsonToBean(salesOrderForm, FormSalesOrderEntity.class);
		List<FormSalesOrderEntryEntity> salesEntryList = JsonUtil.getJsonToList(salesOrderForm.getEntryList(), FormSalesOrderEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(salesOrderForm.getStatus())) {
			salesOrderService.save(sales.getId(), sales, salesEntryList);
			return R.ok(null, "保存成功");
		}
		salesOrderService.submit(sales.getId(), sales, salesEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改销售订单
	 *
	 * @param salesOrderForm 表单对象
	 * @param id             主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改销售订单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody SalesOrderForm salesOrderForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormSalesOrderEntity sales = JsonUtil.getJsonToBean(salesOrderForm, FormSalesOrderEntity.class);
		List<FormSalesOrderEntryEntity> salesEntryList = JsonUtil.getJsonToList(salesOrderForm.getEntryList(), FormSalesOrderEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(salesOrderForm.getStatus())) {
			salesOrderService.save(id, sales, salesEntryList);
			return R.ok(null, "保存成功");
		}
		salesOrderService.submit(id, sales, salesEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
