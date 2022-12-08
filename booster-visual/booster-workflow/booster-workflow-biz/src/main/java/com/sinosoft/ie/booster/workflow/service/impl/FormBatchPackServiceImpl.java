package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormBatchPackEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormBatchPackMapper;
import com.sinosoft.ie.booster.workflow.model.formbatchpack.BatchPackForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormBatchPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 批包装指令
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormBatchPackServiceImpl extends ServiceImpl<FormBatchPackMapper, FormBatchPackEntity> implements FormBatchPackService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormBatchPackEntity getInfo(Long id) {
		QueryWrapper<FormBatchPackEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormBatchPackEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormBatchPackEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormBatchPackEntity entity) throws WorkFlowException {
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
		BatchPackForm batchPackForm = JsonUtil.getJsonToBean(data, BatchPackForm.class);
		FormBatchPackEntity entity = JsonUtil.getJsonToBean(batchPackForm, FormBatchPackEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
