package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormTravelApplyEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormTravelApplyMapper;
import com.sinosoft.ie.booster.workflow.model.formtravelapply.TravelApplyForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormTravelApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 出差预支申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormTravelApplyServiceImpl extends ServiceImpl<FormTravelApplyMapper, FormTravelApplyEntity> implements FormTravelApplyService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormTravelApplyEntity getInfo(Long id) {
		QueryWrapper<FormTravelApplyEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormTravelApplyEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormTravelApplyEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormTravelApplyEntity entity) throws WorkFlowException {
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
		TravelApplyForm travelApplyForm = JsonUtil.getJsonToBean(data, TravelApplyForm.class);
		FormTravelApplyEntity entity = JsonUtil.getJsonToBean(travelApplyForm, FormTravelApplyEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
