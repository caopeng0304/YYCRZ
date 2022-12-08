package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormRewardPunishmentEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormRewardPunishmentMapper;
import com.sinosoft.ie.booster.workflow.model.formrewardpunishment.RewardPunishmentForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 行政赏罚单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormRewardPunishmentServiceImpl extends ServiceImpl<FormRewardPunishmentMapper, FormRewardPunishmentEntity> implements FormRewardPunishmentService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormRewardPunishmentEntity getInfo(Long id) {
		QueryWrapper<FormRewardPunishmentEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormRewardPunishmentEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormRewardPunishmentEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormRewardPunishmentEntity entity) throws WorkFlowException {
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
		RewardPunishmentForm rewardPunishmentForm = JsonUtil.getJsonToBean(data, RewardPunishmentForm.class);
		FormRewardPunishmentEntity entity = JsonUtil.getJsonToBean(rewardPunishmentForm, FormRewardPunishmentEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
