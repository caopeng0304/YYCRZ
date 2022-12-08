package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormQuotationApprovalEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormQuotationApprovalMapper;
import com.sinosoft.ie.booster.workflow.model.formquotationapproval.QuotationApprovalForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormQuotationApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报价审批表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormQuotationApprovalServiceImpl extends ServiceImpl<FormQuotationApprovalMapper, FormQuotationApprovalEntity> implements FormQuotationApprovalService {

	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public FormQuotationApprovalEntity getInfo(Long id) {
		QueryWrapper<FormQuotationApprovalEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormQuotationApprovalEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormQuotationApprovalEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			this.save(entity);
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
	public void submit(Long id, FormQuotationApprovalEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			this.save(entity);
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
		QuotationApprovalForm quotationApprovalForm = JsonUtil.getJsonToBean(data, QuotationApprovalForm.class);
		FormQuotationApprovalEntity entity = JsonUtil.getJsonToBean(quotationApprovalForm, FormQuotationApprovalEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
