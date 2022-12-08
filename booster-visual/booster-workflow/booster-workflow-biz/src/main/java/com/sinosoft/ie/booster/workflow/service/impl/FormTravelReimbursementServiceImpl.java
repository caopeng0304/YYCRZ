package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormTravelReimbursementEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormTravelReimbursementMapper;
import com.sinosoft.ie.booster.workflow.model.formtravelreimbursement.TravelReimbursementForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormTravelReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 差旅报销申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormTravelReimbursementServiceImpl extends ServiceImpl<FormTravelReimbursementMapper, FormTravelReimbursementEntity> implements FormTravelReimbursementService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormTravelReimbursementEntity getInfo(Long id) {
		QueryWrapper<FormTravelReimbursementEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormTravelReimbursementEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormTravelReimbursementEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormTravelReimbursementEntity entity) throws WorkFlowException {
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
		TravelReimbursementForm travelReimbursementForm = JsonUtil.getJsonToBean(data, TravelReimbursementForm.class);
		FormTravelReimbursementEntity entity = JsonUtil.getJsonToBean(travelReimbursementForm, FormTravelReimbursementEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
