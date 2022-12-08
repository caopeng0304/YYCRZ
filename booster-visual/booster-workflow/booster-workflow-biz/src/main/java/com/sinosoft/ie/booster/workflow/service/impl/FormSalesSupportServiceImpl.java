package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormSalesSupportEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormSalesSupportMapper;
import com.sinosoft.ie.booster.workflow.model.formsalessupport.SalesSupportForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormSalesSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 销售支持表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormSalesSupportServiceImpl extends ServiceImpl<FormSalesSupportMapper, FormSalesSupportEntity> implements FormSalesSupportService {

	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public FormSalesSupportEntity getInfo(Long id) {
		QueryWrapper<FormSalesSupportEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormSalesSupportEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormSalesSupportEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
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
	public void submit(Long id, FormSalesSupportEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
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
		SalesSupportForm salesSupportForm = JsonUtil.getJsonToBean(data, SalesSupportForm.class);
		FormSalesSupportEntity entity = JsonUtil.getJsonToBean(salesSupportForm, FormSalesSupportEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
