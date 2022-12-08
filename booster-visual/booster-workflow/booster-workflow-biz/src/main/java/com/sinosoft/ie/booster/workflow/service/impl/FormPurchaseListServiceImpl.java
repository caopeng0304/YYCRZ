package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormPurchaseListEntity;
import com.sinosoft.ie.booster.workflow.entity.FormPurchaseListEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormPurchaseListMapper;
import com.sinosoft.ie.booster.workflow.model.formpurchaselist.PurchaseListForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormPurchaseListEntryService;
import com.sinosoft.ie.booster.workflow.service.FormPurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日常物品采购清单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormPurchaseListServiceImpl extends ServiceImpl<FormPurchaseListMapper, FormPurchaseListEntity> implements FormPurchaseListService {

	@Autowired
	private FormPurchaseListEntryService purchaseListEntryService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public List<FormPurchaseListEntryEntity> getPurchaseEntryList(Long id) {
		QueryWrapper<FormPurchaseListEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormPurchaseListEntryEntity::getPurchaseId, id).orderByDesc(FormPurchaseListEntryEntity::getSort);
		return purchaseListEntryService.list(queryWrapper);
	}

	@Override
	public FormPurchaseListEntity getInfo(Long id) {
		QueryWrapper<FormPurchaseListEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormPurchaseListEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormPurchaseListEntity entity, List<FormPurchaseListEntryEntity> purchaseListEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < purchaseListEntryEntityList.size(); i++) {
				purchaseListEntryEntityList.get(i).setPurchaseId(entity.getId());
				purchaseListEntryEntityList.get(i).setSort(i);
				purchaseListEntryService.save(purchaseListEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			QueryWrapper<FormPurchaseListEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormPurchaseListEntryEntity::getPurchaseId, entity.getId());
			purchaseListEntryService.remove(queryWrapper);
			for (int i = 0; i < purchaseListEntryEntityList.size(); i++) {
				purchaseListEntryEntityList.get(i).setPurchaseId(entity.getId());
				purchaseListEntryEntityList.get(i).setSort(i);
				purchaseListEntryService.save(purchaseListEntryEntityList.get(i));
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
	public void submit(Long id, FormPurchaseListEntity entity, List<FormPurchaseListEntryEntity> purchaseListEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < purchaseListEntryEntityList.size(); i++) {
				purchaseListEntryEntityList.get(i).setPurchaseId(entity.getId());
				purchaseListEntryEntityList.get(i).setSort(i);
				purchaseListEntryService.save(purchaseListEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			QueryWrapper<FormPurchaseListEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormPurchaseListEntryEntity::getPurchaseId, entity.getId());
			purchaseListEntryService.remove(queryWrapper);
			for (int i = 0; i < purchaseListEntryEntityList.size(); i++) {
				purchaseListEntryEntityList.get(i).setPurchaseId(entity.getId());
				purchaseListEntryEntityList.get(i).setSort(i);
				purchaseListEntryService.save(purchaseListEntryEntityList.get(i));
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
		PurchaseListForm purchaseListForm = JsonUtil.getJsonToBean(data, PurchaseListForm.class);
		FormPurchaseListEntity entity = JsonUtil.getJsonToBean(purchaseListForm, FormPurchaseListEntity.class);
		List<FormPurchaseListEntryEntity> purchaseListEntryEntityList = JsonUtil.getJsonToList(purchaseListForm.getEntryList(), FormPurchaseListEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormPurchaseListEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormPurchaseListEntryEntity::getPurchaseId, entity.getId());
		purchaseListEntryService.remove(queryWrapper);
		for (int i = 0; i < purchaseListEntryEntityList.size(); i++) {
			purchaseListEntryEntityList.get(i).setPurchaseId(entity.getId());
			purchaseListEntryEntityList.get(i).setSort(i);
			purchaseListEntryService.save(purchaseListEntryEntityList.get(i));
		}
		//编辑
		this.updateById(entity);
	}
}
