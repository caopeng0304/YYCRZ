package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormPaymentApplyEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormPaymentApplyMapper;
import com.sinosoft.ie.booster.workflow.model.formpaymentapply.PaymentApplyForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormPaymentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 付款申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormPaymentApplyServiceImpl extends ServiceImpl<FormPaymentApplyMapper, FormPaymentApplyEntity> implements FormPaymentApplyService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormPaymentApplyEntity getInfo(Long id) {
		QueryWrapper<FormPaymentApplyEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormPaymentApplyEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormPaymentApplyEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormPaymentApplyEntity entity) throws WorkFlowException {
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
		PaymentApplyForm paymentApplyForm = JsonUtil.getJsonToBean(data, PaymentApplyForm.class);
		FormPaymentApplyEntity entity = JsonUtil.getJsonToBean(paymentApplyForm, FormPaymentApplyEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
