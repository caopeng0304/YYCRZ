package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormWarehouseReceiptEntity;
import com.sinosoft.ie.booster.workflow.entity.FormWarehouseReceiptEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormWarehouseReceiptMapper;
import com.sinosoft.ie.booster.workflow.model.formwarehousereceipt.WarehouseReceiptForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormWarehouseReceiptEntryService;
import com.sinosoft.ie.booster.workflow.service.FormWarehouseReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 入库申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormWarehouseReceiptServiceImpl extends ServiceImpl<FormWarehouseReceiptMapper, FormWarehouseReceiptEntity> implements FormWarehouseReceiptService {

	@Autowired
	private FormWarehouseReceiptEntryService warehouseEntryService;
	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public List<FormWarehouseReceiptEntryEntity> getWarehouseEntryList(Long id) {
		QueryWrapper<FormWarehouseReceiptEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormWarehouseReceiptEntryEntity::getWarehouseId, id).orderByDesc(FormWarehouseReceiptEntryEntity::getSort);
		return warehouseEntryService.list(queryWrapper);
	}

	@Override
	public FormWarehouseReceiptEntity getInfo(Long id) {
		QueryWrapper<FormWarehouseReceiptEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormWarehouseReceiptEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormWarehouseReceiptEntity entity, List<FormWarehouseReceiptEntryEntity> warehouseEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
				warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
				warehouseEntryEntityList.get(i).setSort(i);
				warehouseEntryService.save(warehouseEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormWarehouseReceiptEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormWarehouseReceiptEntryEntity::getWarehouseId, entity.getId());
			warehouseEntryService.remove(queryWrapper);
			for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
				warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
				warehouseEntryEntityList.get(i).setSort(i);
				warehouseEntryService.save(warehouseEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormWarehouseReceiptEntity entity, List<FormWarehouseReceiptEntryEntity> warehouseEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
				warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
				warehouseEntryEntityList.get(i).setSort(i);
				warehouseEntryService.save(warehouseEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormWarehouseReceiptEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormWarehouseReceiptEntryEntity::getWarehouseId, entity.getId());
			warehouseEntryService.remove(queryWrapper);
			for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
				warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
				warehouseEntryEntityList.get(i).setSort(i);
				warehouseEntryService.save(warehouseEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		WarehouseReceiptForm warehouseReceiptForm = JsonUtil.getJsonToBean(data, WarehouseReceiptForm.class);
		FormWarehouseReceiptEntity entity = JsonUtil.getJsonToBean(warehouseReceiptForm, FormWarehouseReceiptEntity.class);
		List<FormWarehouseReceiptEntryEntity> warehouseEntryEntityList = JsonUtil.getJsonToList(warehouseReceiptForm.getEntryList(), FormWarehouseReceiptEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormWarehouseReceiptEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormWarehouseReceiptEntryEntity::getWarehouseId, entity.getId());
		warehouseEntryService.remove(queryWrapper);
		for (int i = 0; i < warehouseEntryEntityList.size(); i++) {
			warehouseEntryEntityList.get(i).setWarehouseId(entity.getId());
			warehouseEntryEntityList.get(i).setSort(i);
			warehouseEntryService.save(warehouseEntryEntityList.get(i));
		}
		//编辑
		this.updateById(entity);
	}
}
