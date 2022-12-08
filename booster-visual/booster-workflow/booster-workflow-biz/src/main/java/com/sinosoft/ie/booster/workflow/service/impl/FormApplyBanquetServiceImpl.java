package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormApplyBanquetEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormApplyBanquetMapper;
import com.sinosoft.ie.booster.workflow.model.formapplybanquet.ApplyBanquetForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormApplyBanquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 宴请申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormApplyBanquetServiceImpl extends ServiceImpl<FormApplyBanquetMapper, FormApplyBanquetEntity> implements FormApplyBanquetService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormApplyBanquetEntity getInfo(Long id) {
		QueryWrapper<FormApplyBanquetEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormApplyBanquetEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormApplyBanquetEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			this.save(entity);
		} else {
			entity.setId(id);
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormApplyBanquetEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			this.save(entity);
		} else {
			entity.setId(id);
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		ApplyBanquetForm applyBanquetForm = JsonUtil.getJsonToBean(data, ApplyBanquetForm.class);
		FormApplyBanquetEntity entity = JsonUtil.getJsonToBean(applyBanquetForm, FormApplyBanquetEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
