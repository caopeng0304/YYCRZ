package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormDebitBillEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormDebitBillMapper;
import com.sinosoft.ie.booster.workflow.model.formdebitbill.DebitBillForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormDebitBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 借支单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormDebitBillServiceImpl extends ServiceImpl<FormDebitBillMapper, FormDebitBillEntity> implements FormDebitBillService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormDebitBillEntity getInfo(Long id) {
		QueryWrapper<FormDebitBillEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormDebitBillEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormDebitBillEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormDebitBillEntity entity) throws WorkFlowException {
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
		DebitBillForm debitBillForm = JsonUtil.getJsonToBean(data, DebitBillForm.class);
		FormDebitBillEntity entity = JsonUtil.getJsonToBean(debitBillForm, FormDebitBillEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
