package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormMaterialRequisitionEntity;
import com.sinosoft.ie.booster.workflow.entity.FormMaterialRequisitionEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormMaterialRequisitionMapper;
import com.sinosoft.ie.booster.workflow.model.formmaterialrequisition.MaterialRequisitionForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormMaterialRequisitionEntryService;
import com.sinosoft.ie.booster.workflow.service.FormMaterialRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 领料单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormMaterialRequisitionServiceImpl extends ServiceImpl<FormMaterialRequisitionMapper, FormMaterialRequisitionEntity> implements FormMaterialRequisitionService {

	@Autowired
	private FormMaterialRequisitionEntryService materialEntryService;
	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public List<FormMaterialRequisitionEntryEntity> getMaterialEntryList(Long id) {
		QueryWrapper<FormMaterialRequisitionEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormMaterialRequisitionEntryEntity::getLeadId, id).orderByDesc(FormMaterialRequisitionEntryEntity::getSort);
		return materialEntryService.list(queryWrapper);
	}

	@Override
	public FormMaterialRequisitionEntity getInfo(Long id) {
		QueryWrapper<FormMaterialRequisitionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormMaterialRequisitionEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormMaterialRequisitionEntity entity, List<FormMaterialRequisitionEntryEntity> materialEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < materialEntryEntityList.size(); i++) {
				materialEntryEntityList.get(i).setLeadId(entity.getId());
				materialEntryEntityList.get(i).setSort(i);
				materialEntryService.save(materialEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormMaterialRequisitionEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormMaterialRequisitionEntryEntity::getLeadId, entity.getId());
			materialEntryService.remove(queryWrapper);
			for (int i = 0; i < materialEntryEntityList.size(); i++) {
				materialEntryEntityList.get(i).setLeadId(entity.getId());
				materialEntryEntityList.get(i).setSort(i);
				materialEntryService.save(materialEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormMaterialRequisitionEntity entity, List<FormMaterialRequisitionEntryEntity> materialEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < materialEntryEntityList.size(); i++) {
				materialEntryEntityList.get(i).setLeadId(entity.getId());
				materialEntryEntityList.get(i).setSort(i);
				materialEntryService.save(materialEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormMaterialRequisitionEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormMaterialRequisitionEntryEntity::getLeadId, entity.getId());
			materialEntryService.remove(queryWrapper);
			for (int i = 0; i < materialEntryEntityList.size(); i++) {
				materialEntryEntityList.get(i).setLeadId(entity.getId());
				materialEntryEntityList.get(i).setSort(i);
				materialEntryService.save(materialEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		MaterialRequisitionForm materialRequisitionForm = JsonUtil.getJsonToBean(data, MaterialRequisitionForm.class);
		FormMaterialRequisitionEntity entity = JsonUtil.getJsonToBean(materialRequisitionForm, FormMaterialRequisitionEntity.class);
		List<FormMaterialRequisitionEntryEntity> materialEntryEntityList = JsonUtil.getJsonToList(materialRequisitionForm.getEntryList(), FormMaterialRequisitionEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormMaterialRequisitionEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormMaterialRequisitionEntryEntity::getLeadId, entity.getId());
		materialEntryService.remove(queryWrapper);
		for (int i = 0; i < materialEntryEntityList.size(); i++) {
			materialEntryEntityList.get(i).setLeadId(entity.getId());
			materialEntryEntityList.get(i).setSort(i);
			materialEntryService.save(materialEntryEntityList.get(i));
		}
		this.updateById(entity);
	}
}
