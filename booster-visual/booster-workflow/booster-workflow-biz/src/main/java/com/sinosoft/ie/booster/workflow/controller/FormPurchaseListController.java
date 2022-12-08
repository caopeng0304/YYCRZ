package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormPurchaseListEntity;
import com.sinosoft.ie.booster.workflow.entity.FormPurchaseListEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formpurchaselist.PurchaseListEntryEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formpurchaselist.PurchaseListForm;
import com.sinosoft.ie.booster.workflow.model.formpurchaselist.PurchaseListInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormPurchaseListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 日常物品采购清单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "日常物品采购清单", value = "PurchaseList")
@RestController
@RequestMapping("/Form/PurchaseList")
public class FormPurchaseListController {

	@Autowired
	private FormPurchaseListService purchaseListService;

	/**
	 * 获取日常物品采购清单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取日常物品采购清单信息")
	@GetMapping("/{id}")
	public R<PurchaseListInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormPurchaseListEntity entity = purchaseListService.getInfo(id);
		List<FormPurchaseListEntryEntity> entityList = purchaseListService.getPurchaseEntryList(id);
		PurchaseListInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PurchaseListInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, PurchaseListEntryEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建日常物品采购清单
	 *
	 * @param purchaseListForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建日常物品采购清单")
	@PostMapping
	public R<Boolean> create(@RequestBody PurchaseListForm purchaseListForm) throws WorkFlowException {
		FormPurchaseListEntity procurement = JsonUtil.getJsonToBean(purchaseListForm, FormPurchaseListEntity.class);
		List<FormPurchaseListEntryEntity> procurementEntryList = JsonUtil.getJsonToList(purchaseListForm.getEntryList(), FormPurchaseListEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(purchaseListForm.getStatus())) {
			purchaseListService.save(procurement.getId(), procurement, procurementEntryList);
			return R.ok(null, "保存成功");
		}
		purchaseListService.submit(procurement.getId(), procurement, procurementEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改日常物品采购清单
	 *
	 * @param purchaseListForm 表单对象
	 * @param id               主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改日常物品采购清单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody PurchaseListForm purchaseListForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormPurchaseListEntity procurement = JsonUtil.getJsonToBean(purchaseListForm, FormPurchaseListEntity.class);
		List<FormPurchaseListEntryEntity> procurementEntryList = JsonUtil.getJsonToList(purchaseListForm.getEntryList(), FormPurchaseListEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(purchaseListForm.getStatus())) {
			purchaseListService.save(id, procurement, procurementEntryList);
			return R.ok(null, "保存成功");
		}
		purchaseListService.submit(id, procurement, procurementEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
