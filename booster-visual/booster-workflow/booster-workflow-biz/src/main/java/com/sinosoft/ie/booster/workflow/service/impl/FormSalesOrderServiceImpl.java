package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormSalesOrderEntity;
import com.sinosoft.ie.booster.workflow.entity.FormSalesOrderEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormSalesOrderMapper;
import com.sinosoft.ie.booster.workflow.model.formsalesorder.SalesOrderForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormSalesOrderEntryService;
import com.sinosoft.ie.booster.workflow.service.FormSalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 销售订单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormSalesOrderServiceImpl extends ServiceImpl<FormSalesOrderMapper, FormSalesOrderEntity> implements FormSalesOrderService {

	@Autowired
	private FormSalesOrderEntryService salesOrderEntryService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public List<FormSalesOrderEntryEntity> getSalesEntryList(Long id) {
		QueryWrapper<FormSalesOrderEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormSalesOrderEntryEntity::getSalesOrderId, id).orderByDesc(FormSalesOrderEntryEntity::getSort);
		return salesOrderEntryService.list(queryWrapper);
	}

	@Override
	public FormSalesOrderEntity getInfo(Long id) {
		QueryWrapper<FormSalesOrderEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormSalesOrderEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormSalesOrderEntity entity, List<FormSalesOrderEntryEntity> salesOrderEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < salesOrderEntryEntityList.size(); i++) {
				salesOrderEntryEntityList.get(i).setSalesOrderId(entity.getId());
				salesOrderEntryEntityList.get(i).setSort(i);
				salesOrderEntryService.save(salesOrderEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			QueryWrapper<FormSalesOrderEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormSalesOrderEntryEntity::getSalesOrderId, entity.getId());
			salesOrderEntryService.remove(queryWrapper);
			for (int i = 0; i < salesOrderEntryEntityList.size(); i++) {
				salesOrderEntryEntityList.get(i).setSalesOrderId(entity.getId());
				salesOrderEntryEntityList.get(i).setSort(i);
				salesOrderEntryService.save(salesOrderEntryEntityList.get(i));
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
	public void submit(Long id, FormSalesOrderEntity entity, List<FormSalesOrderEntryEntity> salesOrderEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < salesOrderEntryEntityList.size(); i++) {
				salesOrderEntryEntityList.get(i).setSalesOrderId(entity.getId());
				salesOrderEntryEntityList.get(i).setSort(i);
				salesOrderEntryService.save(salesOrderEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			QueryWrapper<FormSalesOrderEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormSalesOrderEntryEntity::getSalesOrderId, entity.getId());
			salesOrderEntryService.remove(queryWrapper);
			for (int i = 0; i < salesOrderEntryEntityList.size(); i++) {
				salesOrderEntryEntityList.get(i).setSalesOrderId(entity.getId());
				salesOrderEntryEntityList.get(i).setSort(i);
				salesOrderEntryService.save(salesOrderEntryEntityList.get(i));
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
		SalesOrderForm salesOrderForm = JsonUtil.getJsonToBean(data, SalesOrderForm.class);
		FormSalesOrderEntity entity = JsonUtil.getJsonToBean(salesOrderForm, FormSalesOrderEntity.class);
		List<FormSalesOrderEntryEntity> salesOrderEntryEntityList = JsonUtil.getJsonToList(salesOrderForm.getEntryList(), FormSalesOrderEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormSalesOrderEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormSalesOrderEntryEntity::getSalesOrderId, entity.getId());
		salesOrderEntryService.remove(queryWrapper);
		for (int i = 0; i < salesOrderEntryEntityList.size(); i++) {
			salesOrderEntryEntityList.get(i).setSalesOrderId(entity.getId());
			salesOrderEntryEntityList.get(i).setSort(i);
			salesOrderEntryService.save(salesOrderEntryEntityList.get(i));
		}
		//编辑
		this.updateById(entity);
	}
}
