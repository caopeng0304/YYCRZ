package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormProcurementMaterialEntity;
import com.sinosoft.ie.booster.workflow.entity.FormProcurementMaterialEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormProcurementMaterialMapper;
import com.sinosoft.ie.booster.workflow.model.formprocurementmaterial.ProcurementMaterialForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormProcurementMaterialEntryService;
import com.sinosoft.ie.booster.workflow.service.FormProcurementMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购原材料
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormProcurementMaterialServiceImpl extends ServiceImpl<FormProcurementMaterialMapper, FormProcurementMaterialEntity> implements FormProcurementMaterialService {

	@Autowired
	private FormProcurementMaterialEntryService procurementEntryEntityService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public List<FormProcurementMaterialEntryEntity> getProcurementEntryList(Long id) {
		QueryWrapper<FormProcurementMaterialEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormProcurementMaterialEntryEntity::getProcurementId, id).orderByDesc(FormProcurementMaterialEntryEntity::getSort);
		return procurementEntryEntityService.list(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public FormProcurementMaterialEntity getInfo(Long id) {
		QueryWrapper<FormProcurementMaterialEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormProcurementMaterialEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormProcurementMaterialEntity entity, List<FormProcurementMaterialEntryEntity> procurementEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < procurementEntryEntityList.size(); i++) {
				procurementEntryEntityList.get(i).setProcurementId(entity.getId());
				procurementEntryEntityList.get(i).setSort(i);
				procurementEntryEntityService.save(procurementEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			QueryWrapper<FormProcurementMaterialEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormProcurementMaterialEntryEntity::getProcurementId, entity.getId());
			procurementEntryEntityService.remove(queryWrapper);
			for (int i = 0; i < procurementEntryEntityList.size(); i++) {
				procurementEntryEntityList.get(i).setProcurementId(entity.getId());
				procurementEntryEntityList.get(i).setSort(i);
				procurementEntryEntityService.save(procurementEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
			//更新附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.updateFile(data);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormProcurementMaterialEntity entity, List<FormProcurementMaterialEntryEntity> procurementEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < procurementEntryEntityList.size(); i++) {
				procurementEntryEntityList.get(i).setProcurementId(entity.getId());
				procurementEntryEntityList.get(i).setSort(i);
				procurementEntryEntityService.save(procurementEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			QueryWrapper<FormProcurementMaterialEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormProcurementMaterialEntryEntity::getProcurementId, entity.getId());
			procurementEntryEntityService.remove(queryWrapper);
			for (int i = 0; i < procurementEntryEntityList.size(); i++) {
				procurementEntryEntityList.get(i).setProcurementId(entity.getId());
				procurementEntryEntityList.get(i).setSort(i);
				procurementEntryEntityService.save(procurementEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
			//更新附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.updateFile(data);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		ProcurementMaterialForm procurementMaterialForm = JsonUtil.getJsonToBean(data, ProcurementMaterialForm.class);
		FormProcurementMaterialEntity entity = JsonUtil.getJsonToBean(procurementMaterialForm, FormProcurementMaterialEntity.class);
		List<FormProcurementMaterialEntryEntity> procurementEntryEntityList = JsonUtil.getJsonToList(procurementMaterialForm.getEntryList(), FormProcurementMaterialEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormProcurementMaterialEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormProcurementMaterialEntryEntity::getProcurementId, entity.getId());
		procurementEntryEntityService.remove(queryWrapper);
		for (int i = 0; i < procurementEntryEntityList.size(); i++) {
			procurementEntryEntityList.get(i).setProcurementId(entity.getId());
			procurementEntryEntityList.get(i).setSort(i);
			procurementEntryEntityService.save(procurementEntryEntityList.get(i));
		}
		//编辑
		this.updateById(entity);
	}
}
