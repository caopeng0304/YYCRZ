package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskOperatorRecordEntity;
import com.sinosoft.ie.booster.workflow.mapper.FlowTaskOperatorRecordMapper;
import com.sinosoft.ie.booster.workflow.service.FlowTaskOperatorRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程经办记录
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowTaskOperatorRecordServiceImpl extends ServiceImpl<FlowTaskOperatorRecordMapper, FlowTaskOperatorRecordEntity> implements FlowTaskOperatorRecordService {

	@Override
	public List<FlowTaskOperatorRecordEntity> getList(Long taskId) {
		QueryWrapper<FlowTaskOperatorRecordEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorRecordEntity::getTaskId, taskId).orderByAsc(FlowTaskOperatorRecordEntity::getHandleTime);
		return this.list(queryWrapper);
	}

	@Override
	public FlowTaskOperatorRecordEntity getInfo(Long id) {
		QueryWrapper<FlowTaskOperatorRecordEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorRecordEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(FlowTaskOperatorRecordEntity entity) {
		QueryWrapper<FlowTaskOperatorRecordEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskOperatorRecordEntity::getId, entity.getId());
		this.remove(queryWrapper);
	}

	@Override
	public void create(FlowTaskOperatorRecordEntity entity) {
		this.save(entity);
	}

	@Override
	public void update(Long id, FlowTaskOperatorRecordEntity entity) {
		entity.setId(id);
		this.updateById(entity);
	}
}
