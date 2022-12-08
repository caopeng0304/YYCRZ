package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormOutGoingApplyEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormOutGoingApplyMapper;
import com.sinosoft.ie.booster.workflow.model.formoutgoingapply.OutgoingApplyForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormOutGoingApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 外出申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormOutGoingApplyServiceImpl extends ServiceImpl<FormOutGoingApplyMapper, FormOutGoingApplyEntity> implements FormOutGoingApplyService {

	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public FormOutGoingApplyEntity getInfo(Long id) {
		QueryWrapper<FormOutGoingApplyEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOutGoingApplyEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormOutGoingApplyEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormOutGoingApplyEntity entity) throws WorkFlowException {
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
		OutgoingApplyForm outgoingApplyForm = JsonUtil.getJsonToBean(data, OutgoingApplyForm.class);
		FormOutGoingApplyEntity entity = JsonUtil.getJsonToBean(outgoingApplyForm, FormOutGoingApplyEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
