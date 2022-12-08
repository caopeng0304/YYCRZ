package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormOfficeSuppliesEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormOfficeSuppliesMapper;
import com.sinosoft.ie.booster.workflow.model.formofficesupplies.OfficeSuppliesForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormOfficeSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 领用办公用品申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormOfficeSuppliesServiceImpl extends ServiceImpl<FormOfficeSuppliesMapper, FormOfficeSuppliesEntity> implements FormOfficeSuppliesService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormOfficeSuppliesEntity getInfo(Long id) {
		QueryWrapper<FormOfficeSuppliesEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOfficeSuppliesEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormOfficeSuppliesEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormOfficeSuppliesEntity entity) throws WorkFlowException {
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
		OfficeSuppliesForm officeSuppliesForm = JsonUtil.getJsonToBean(data, OfficeSuppliesForm.class);
		FormOfficeSuppliesEntity entity = JsonUtil.getJsonToBean(officeSuppliesForm, FormOfficeSuppliesEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
