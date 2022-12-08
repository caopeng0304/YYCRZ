package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormFinishedProductEntity;
import com.sinosoft.ie.booster.workflow.entity.FormFinishedProductEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormFinishedProductMapper;
import com.sinosoft.ie.booster.workflow.model.formfinishedproduct.FinishedProductForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormFinishedProductEntryService;
import com.sinosoft.ie.booster.workflow.service.FormFinishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 成品入库单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormFinishedProductServiceImpl extends ServiceImpl<FormFinishedProductMapper, FormFinishedProductEntity> implements FormFinishedProductService {

	@Autowired
	private FormFinishedProductEntryService finishedProductEntryService;
	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public List<FormFinishedProductEntryEntity> getFinishedEntryList(Long id) {
		QueryWrapper<FormFinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormFinishedProductEntryEntity::getWarehouseId, id).orderByDesc(FormFinishedProductEntryEntity::getSort);
		return finishedProductEntryService.list(queryWrapper);
	}

	@Override
	public FormFinishedProductEntity getInfo(Long id) {
		QueryWrapper<FormFinishedProductEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormFinishedProductEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormFinishedProductEntity entity, List<FormFinishedProductEntryEntity> finishedProductEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
				finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
				finishedProductEntryEntityList.get(i).setSort(i);
				finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormFinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormFinishedProductEntryEntity::getWarehouseId, entity.getId());
			finishedProductEntryService.remove(queryWrapper);
			for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
				finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
				finishedProductEntryEntityList.get(i).setSort(i);
				finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormFinishedProductEntity entity, List<FormFinishedProductEntryEntity> finishedProductEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
				finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
				finishedProductEntryEntityList.get(i).setSort(i);
				finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormFinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormFinishedProductEntryEntity::getWarehouseId, entity.getId());
			finishedProductEntryService.remove(queryWrapper);
			for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
				finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
				finishedProductEntryEntityList.get(i).setSort(i);
				finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		FinishedProductForm finishedProductForm = JsonUtil.getJsonToBean(data, FinishedProductForm.class);
		FormFinishedProductEntity entity = JsonUtil.getJsonToBean(finishedProductForm, FormFinishedProductEntity.class);
		List<FormFinishedProductEntryEntity> finishedProductEntryEntityList = JsonUtil.getJsonToList(finishedProductForm.getEntryList(), FormFinishedProductEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormFinishedProductEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormFinishedProductEntryEntity::getWarehouseId, entity.getId());
		finishedProductEntryService.remove(queryWrapper);
		for (int i = 0; i < finishedProductEntryEntityList.size(); i++) {
			finishedProductEntryEntityList.get(i).setWarehouseId(entity.getId());
			finishedProductEntryEntityList.get(i).setSort(i);
			finishedProductEntryService.save(finishedProductEntryEntityList.get(i));
		}
		this.updateById(entity);
	}
}
