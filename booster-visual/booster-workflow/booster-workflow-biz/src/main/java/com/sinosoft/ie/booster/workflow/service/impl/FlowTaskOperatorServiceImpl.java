package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskOperatorEntity;
import com.sinosoft.ie.booster.workflow.mapper.FlowTaskOperatorMapper;
import com.sinosoft.ie.booster.workflow.service.FlowTaskOperatorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 流程经办
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowTaskOperatorServiceImpl extends ServiceImpl<FlowTaskOperatorMapper, FlowTaskOperatorEntity> implements FlowTaskOperatorService {

	@Override
	public List<FlowTaskOperatorEntity> getList(Long taskId) {
		QueryWrapper<FlowTaskOperatorEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorEntity::getTaskId, taskId).orderByDesc(FlowTaskOperatorEntity::getCreateTime);
		return this.list(queryWrapper);
	}

	@Override
	public FlowTaskOperatorEntity getInfo(Long id) {
		QueryWrapper<FlowTaskOperatorEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public FlowTaskOperatorEntity getInfo(Long taskId, String nodeNo) {
		QueryWrapper<FlowTaskOperatorEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorEntity::getTaskId, taskId)
				.eq(FlowTaskOperatorEntity::getNodeCode, nodeNo)
				.eq(FlowTaskOperatorEntity::getHandleId, SecurityUtils.getUser().getUsername())
				.eq(FlowTaskOperatorEntity::getCompletion, 0);
		return this.getOne(queryWrapper);
	}

	@Override
	public void deleteByTaskId(Long taskId) {
		QueryWrapper<FlowTaskOperatorEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorEntity::getTaskId, taskId);
		this.remove(queryWrapper);
	}

	@Override
	public void deleteByNodeId(Long nodeId) {
		QueryWrapper<FlowTaskOperatorEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorEntity::getTaskNodeId, nodeId).eq(FlowTaskOperatorEntity::getCompletion, 0);
		this.remove(queryWrapper);
	}

	@Override
	public void create(List<FlowTaskOperatorEntity> entitys) {
		for (FlowTaskOperatorEntity entity : entitys) {
			this.save(entity);
		}
	}

	@Override
	public void update(FlowTaskOperatorEntity entity) {
		this.updateById(entity);
	}

	@Override
	public void update(Long taskNodeId, List<String> userNames) {
		if (userNames.size() > 0) {
			this.baseMapper.updateDelegateUser(taskNodeId, "'" + String.join("','", userNames) + "'");
		}
	}

	@Override
	public void update(Long taskNodeId, String type) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("taskNodeId", taskNodeId);
		map.put("type", type);
		this.baseMapper.updateFixedapprover(map);
	}

	@Override
	public void update(Long taskId) {
		this.baseMapper.updateState(taskId);
	}

	@Override
	public List<FlowTaskOperatorEntity> press(String[] nodeCode, Long taskId) {
		QueryWrapper<FlowTaskOperatorEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().in(FlowTaskOperatorEntity::getNodeCode, nodeCode)
				.eq(FlowTaskOperatorEntity::getCompletion, 0)
				.eq(FlowTaskOperatorEntity::getTaskId, taskId);
		return this.list(queryWrapper);
	}

	@Override
	public void updateReject(Long taskId, Set<String> nodeId) {
		if (nodeId.size() > 0) {
			this.baseMapper.updateReject(taskId, "'" + String.join("','", nodeId) + "'");
		}
	}
}
