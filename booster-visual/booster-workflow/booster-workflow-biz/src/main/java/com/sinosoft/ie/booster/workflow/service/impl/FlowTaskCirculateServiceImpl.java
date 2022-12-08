package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskCirculateEntity;
import com.sinosoft.ie.booster.workflow.mapper.FlowTaskCirculateMapper;
import com.sinosoft.ie.booster.workflow.service.FlowTaskCirculateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程传阅
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowTaskCirculateServiceImpl extends ServiceImpl<FlowTaskCirculateMapper, FlowTaskCirculateEntity> implements FlowTaskCirculateService {

	@Override
	public void deleteByTaskId(Long taskId) {
		QueryWrapper<FlowTaskCirculateEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskCirculateEntity::getTaskId, taskId);
		this.remove(queryWrapper);
	}

	@Override
	public void deleteByNodeId(Long nodeId) {
		QueryWrapper<FlowTaskCirculateEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskCirculateEntity::getTaskNodeId, nodeId);
		this.remove(queryWrapper);
	}

	@Override
	public void create(List<FlowTaskCirculateEntity> entitys) {
		for (FlowTaskCirculateEntity entity : entitys) {
			this.save(entity);
		}
	}
}
