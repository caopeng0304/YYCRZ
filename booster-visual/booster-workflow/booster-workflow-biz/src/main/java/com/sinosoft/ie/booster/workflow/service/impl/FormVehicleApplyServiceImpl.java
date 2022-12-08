package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormVehicleApplyEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormVehicleApplyMapper;
import com.sinosoft.ie.booster.workflow.model.formvehicleapply.VehicleApplyForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormVehicleApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 车辆申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormVehicleApplyServiceImpl extends ServiceImpl<FormVehicleApplyMapper, FormVehicleApplyEntity> implements FormVehicleApplyService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormVehicleApplyEntity getInfo(Long id) {
		QueryWrapper<FormVehicleApplyEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormVehicleApplyEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormVehicleApplyEntity entity) throws WorkFlowException {
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
	public void submit(Long id, FormVehicleApplyEntity entity) throws WorkFlowException {
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
		VehicleApplyForm vehicleApplyForm = JsonUtil.getJsonToBean(data, VehicleApplyForm.class);
		FormVehicleApplyEntity entity = JsonUtil.getJsonToBean(vehicleApplyForm, FormVehicleApplyEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
