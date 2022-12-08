package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormWarehouseReceiptEntity;
import com.sinosoft.ie.booster.workflow.entity.FormWarehouseReceiptEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formwarehousereceipt.WarehouseReceiptEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formwarehousereceipt.WarehouseReceiptForm;
import com.sinosoft.ie.booster.workflow.model.formwarehousereceipt.WarehouseReceiptInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormWarehouseReceiptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 入库申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "入库申请单", value = "WarehouseReceipt")
@RestController
@RequestMapping("/Form/WarehouseReceipt")
public class FormWarehouseReceiptController {

	@Autowired
	private FormWarehouseReceiptService warehouseReceiptService;

	/**
	 * 获取入库申请单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取入库申请单信息")
	@GetMapping("/{id}")
	public R<WarehouseReceiptInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormWarehouseReceiptEntity entity = warehouseReceiptService.getInfo(id);
		List<FormWarehouseReceiptEntryEntity> entityList = warehouseReceiptService.getWarehouseEntryList(id);
		WarehouseReceiptInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, WarehouseReceiptInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, WarehouseReceiptEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建入库申请单
	 *
	 * @param warehouseReceiptForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建入库申请单")
	@PostMapping
	public R<Boolean> create(@RequestBody WarehouseReceiptForm warehouseReceiptForm) throws WorkFlowException {
		FormWarehouseReceiptEntity warehouse = JsonUtil.getJsonToBean(warehouseReceiptForm, FormWarehouseReceiptEntity.class);
		List<FormWarehouseReceiptEntryEntity> warehouseEntryList = JsonUtil.getJsonToList(warehouseReceiptForm.getEntryList(), FormWarehouseReceiptEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(warehouseReceiptForm.getStatus())) {
			warehouseReceiptService.save(warehouse.getId(), warehouse, warehouseEntryList);
			return R.ok(null, "保存成功");
		}
		warehouseReceiptService.submit(warehouse.getId(), warehouse, warehouseEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改入库申请单
	 *
	 * @param warehouseReceiptForm 表单对象
	 * @param id                   主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改入库申请单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody WarehouseReceiptForm warehouseReceiptForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormWarehouseReceiptEntity warehouse = JsonUtil.getJsonToBean(warehouseReceiptForm, FormWarehouseReceiptEntity.class);
		List<FormWarehouseReceiptEntryEntity> warehouseEntryList = JsonUtil.getJsonToList(warehouseReceiptForm.getEntryList(), FormWarehouseReceiptEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(warehouseReceiptForm.getStatus())) {
			warehouseReceiptService.save(id, warehouse, warehouseEntryList);
			return R.ok(null, "保存成功");
		}
		warehouseReceiptService.submit(id, warehouse, warehouseEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
