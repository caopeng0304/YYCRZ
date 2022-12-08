package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormMaterialRequisitionEntity;
import com.sinosoft.ie.booster.workflow.entity.FormMaterialRequisitionEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formmaterialrequisition.MaterialEntryEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formmaterialrequisition.MaterialRequisitionForm;
import com.sinosoft.ie.booster.workflow.model.formmaterialrequisition.MaterialRequisitionInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormMaterialRequisitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 领料单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "领料单", value = "MaterialRequisition")
@RestController
@RequestMapping("/Form/MaterialRequisition")
public class FormMaterialRequisitionController {

	@Autowired
	private FormMaterialRequisitionService materialRequisitionService;

	/**
	 * 获取领料单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取领料单信息")
	@GetMapping("/{id}")
	public R<MaterialRequisitionInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormMaterialRequisitionEntity entity = materialRequisitionService.getInfo(id);
		List<FormMaterialRequisitionEntryEntity> entityList = materialRequisitionService.getMaterialEntryList(id);
		MaterialRequisitionInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, MaterialRequisitionInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, MaterialEntryEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建领料单
	 *
	 * @param materialRequisitionForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建领料单")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid MaterialRequisitionForm materialRequisitionForm) throws WorkFlowException {
		FormMaterialRequisitionEntity material = JsonUtil.getJsonToBean(materialRequisitionForm, FormMaterialRequisitionEntity.class);
		List<FormMaterialRequisitionEntryEntity> materialEntryList = JsonUtil.getJsonToList(materialRequisitionForm.getEntryList(), FormMaterialRequisitionEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(materialRequisitionForm.getStatus())) {
			materialRequisitionService.save(material.getId(), material, materialEntryList);
			return R.ok(null, "保存成功");
		}
		materialRequisitionService.submit(material.getId(), material, materialEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改领料单
	 *
	 * @param materialRequisitionForm 表单对象
	 * @param id                      主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改领料单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid MaterialRequisitionForm materialRequisitionForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormMaterialRequisitionEntity material = JsonUtil.getJsonToBean(materialRequisitionForm, FormMaterialRequisitionEntity.class);
		List<FormMaterialRequisitionEntryEntity> materialEntryList = JsonUtil.getJsonToList(materialRequisitionForm.getEntryList(), FormMaterialRequisitionEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(materialRequisitionForm.getStatus())) {
			materialRequisitionService.save(id, material, materialEntryList);
			return R.ok(null, "保存成功");
		}
		materialRequisitionService.submit(id, material, materialEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
