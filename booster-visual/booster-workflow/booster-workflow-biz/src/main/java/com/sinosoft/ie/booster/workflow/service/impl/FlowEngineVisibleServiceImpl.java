package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineVisibleEntity;
import com.sinosoft.ie.booster.workflow.mapper.FlowEngineVisibleMapper;
import com.sinosoft.ie.booster.workflow.service.FlowEngineVisibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程可见
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowEngineVisibleServiceImpl extends ServiceImpl<FlowEngineVisibleMapper, FlowEngineVisibleEntity> implements FlowEngineVisibleService {

	@Autowired
	private RemotePositionService positionService;

	@Override
	public List<FlowEngineVisibleEntity> getList(Long flowId) {
		QueryWrapper<FlowEngineVisibleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowEngineVisibleEntity::getFlowId, flowId).orderByAsc(FlowEngineVisibleEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public List<FlowEngineVisibleEntity> getVisibleFlowList(String userName) {
		List<SysPositionEntity> positionEntities = positionService.getListByUserName(userName).getData();
		List<String> ids = positionEntities.stream().map(SysPositionEntity::getId).map(String::valueOf).collect(Collectors.toList());
		ids.add(userName);
		List<String> sqlInIds = ids.stream().map(r -> "'" + r + "'").collect(Collectors.toList());
		return this.baseMapper.getVisibleFlowList(String.join(",", sqlInIds));
	}
}
