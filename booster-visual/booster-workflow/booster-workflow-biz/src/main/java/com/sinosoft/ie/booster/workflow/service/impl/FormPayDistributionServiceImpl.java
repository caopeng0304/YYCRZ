package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormPayDistributionEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormPayDistributionMapper;
import com.sinosoft.ie.booster.workflow.model.formpaydistribution.PayDistributionForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormPayDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 薪酬发放
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormPayDistributionServiceImpl extends ServiceImpl<FormPayDistributionMapper, FormPayDistributionEntity> implements FormPayDistributionService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormPayDistributionEntity getInfo(Long id) {
		QueryWrapper<FormPayDistributionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormPayDistributionEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormPayDistributionEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormPayDistributionEntity entity) throws WorkFlowException {
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
		PayDistributionForm payDistributionForm = JsonUtil.getJsonToBean(data, PayDistributionForm.class);
		FormPayDistributionEntity entity = JsonUtil.getJsonToBean(payDistributionForm, FormPayDistributionEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
