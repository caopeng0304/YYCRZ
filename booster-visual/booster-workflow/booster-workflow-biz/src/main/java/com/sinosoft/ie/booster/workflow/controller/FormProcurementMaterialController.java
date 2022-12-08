package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormProcurementMaterialEntity;
import com.sinosoft.ie.booster.workflow.entity.FormProcurementMaterialEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formprocurementmaterial.ProcurementEntryEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formprocurementmaterial.ProcurementMaterialForm;
import com.sinosoft.ie.booster.workflow.model.formprocurementmaterial.ProcurementMaterialInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormProcurementMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 采购原材料
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "采购原材料", value = "ProcurementMaterial")
@RestController
@RequestMapping("/Form/ProcurementMaterial")
public class FormProcurementMaterialController {

	@Autowired
	private FormProcurementMaterialService procurementMaterialService;

	/**
	 * 获取采购原材料信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取采购原材料信息")
	@GetMapping("/{id}")
	public R<ProcurementMaterialInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormProcurementMaterialEntity entity = procurementMaterialService.getInfo(id);
		List<FormProcurementMaterialEntryEntity> entityList = procurementMaterialService.getProcurementEntryList(id);
		ProcurementMaterialInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ProcurementMaterialInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, ProcurementEntryEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建采购原材料
	 *
	 * @param procurementMaterialForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建采购原材料")
	@PostMapping
	public R<Boolean> create(@RequestBody ProcurementMaterialForm procurementMaterialForm) throws WorkFlowException {
		FormProcurementMaterialEntity procurement = JsonUtil.getJsonToBean(procurementMaterialForm, FormProcurementMaterialEntity.class);
		List<FormProcurementMaterialEntryEntity> procurementEntryList = JsonUtil.getJsonToList(procurementMaterialForm.getEntryList(), FormProcurementMaterialEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(procurementMaterialForm.getStatus())) {
			procurementMaterialService.save(procurement.getId(), procurement, procurementEntryList);
			return R.ok(null, "保存成功");
		}
		procurementMaterialService.submit(procurement.getId(), procurement, procurementEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改采购原材料
	 *
	 * @param procurementMaterialForm 表单对象
	 * @param id                      主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改采购原材料")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody ProcurementMaterialForm procurementMaterialForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormProcurementMaterialEntity procurement = JsonUtil.getJsonToBean(procurementMaterialForm, FormProcurementMaterialEntity.class);
		List<FormProcurementMaterialEntryEntity> procurementEntryList = JsonUtil.getJsonToList(procurementMaterialForm.getEntryList(), FormProcurementMaterialEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(procurementMaterialForm.getStatus())) {
			procurementMaterialService.save(id, procurement, procurementEntryList);
			return R.ok(null, "保存成功");
		}
		procurementMaterialService.submit(id, procurement, procurementEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
