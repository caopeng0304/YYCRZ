package com.sinosoft.ie.booster.workflow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineVisibleEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskNodeEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowTaskOperatorEnum;
import com.sinosoft.ie.booster.workflow.mapper.FlowEngineMapper;
import com.sinosoft.ie.booster.workflow.model.flowengine.PaginationFlowEngine;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.ChildNodeList;
import com.sinosoft.ie.booster.workflow.service.FlowEngineService;
import com.sinosoft.ie.booster.workflow.service.FlowEngineVisibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程引擎
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowEngineServiceImpl extends ServiceImpl<FlowEngineMapper, FlowEngineEntity> implements FlowEngineService {

	@Autowired
	private FlowEngineVisibleService flowEngineVisibleService;

	@Override
	public List<FlowEngineEntity> getList(PaginationFlowEngine pagination) {
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotEmpty(pagination.getKeyword())) {
			queryWrapper.lambda().like(FlowEngineEntity::getFullName, pagination.getKeyword());
		}
		if (StrUtil.isNotEmpty(pagination.getFormType())) {
			queryWrapper.lambda().like(FlowEngineEntity::getFormType, pagination.getFormType());
		}
		if (StrUtil.isNotEmpty(pagination.getEnabledFlag())) {
			queryWrapper.lambda().like(FlowEngineEntity::getEnabledFlag, pagination.getEnabledFlag());
		}
		//排序
		queryWrapper.lambda().orderByAsc(FlowEngineEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public List<FlowEngineEntity> getList() {
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByDesc(FlowEngineEntity::getCreateTime);
		return this.list(queryWrapper);
	}

	@Override
	public List<FlowEngineEntity> getFlowFormList() {
		List<FlowEngineEntity> data = new ArrayList<>();
		List<FlowEngineEntity> flowEngineData = getList().stream().filter(t -> "1".equals(String.valueOf(t.getEnabledFlag())) && t.getType() == 0).collect(Collectors.toList());
		//部分看见(岗位和用户)
		List<FlowEngineVisibleEntity> flowVisibleData = flowEngineVisibleService.getVisibleFlowList(SecurityUtils.getUser().getUsername());
		for (FlowEngineVisibleEntity item : flowVisibleData) {
			List<FlowEngineEntity> FlowEngine = flowEngineData.stream().filter(m -> String.valueOf(m.getId()).equals(String.valueOf(item.getFlowId()))).collect(Collectors.toList());
			if (FlowEngine.size() > 0) {
				data.addAll(FlowEngine);
			}
		}
		//全部看见
		List<FlowEngineEntity> datas = flowEngineData.stream().filter(m -> "0".equals(String.valueOf(m.getVisibleType()))).collect(Collectors.toList());
		data.addAll(datas);
		//去掉重复数据
		data = data.stream().distinct().collect(Collectors.toList());
		return data;
	}


	@Override
	public FlowEngineEntity getInfo(Long id) throws WorkFlowException {
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowEngineEntity::getId, id);
		FlowEngineEntity FlowEngine = this.getOne(queryWrapper);
		if (FlowEngine == null) {
			throw new WorkFlowException("未找到流程引擎");
		}
		return FlowEngine;
	}

	@Override
	public FlowEngineEntity getInfoByEnCode(String enCode) throws WorkFlowException {
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowEngineEntity::getEncode, enCode).eq(FlowEngineEntity::getEnabledFlag, 1);
		FlowEngineEntity FlowEngine = this.getOne(queryWrapper);
		if (FlowEngine == null) {
			throw new WorkFlowException("未找到流程引擎");
		}
		return FlowEngine;
	}

	@Override
	public boolean isExistByFullName(String fullName, Long id) {
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowEngineEntity::getFullName, fullName);
		if (id != null) {
			queryWrapper.lambda().ne(FlowEngineEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public boolean isExistByEnCode(String enCode, Long id) {
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowEngineEntity::getEncode, enCode);
		if (id != null) {
			queryWrapper.lambda().ne(FlowEngineEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public void delete(FlowEngineEntity entity) {
		this.removeById(entity.getId());
		QueryWrapper<FlowEngineVisibleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowEngineVisibleEntity::getFlowId, entity.getId());
		flowEngineVisibleService.remove(queryWrapper);
	}

	@Override
	public void create(FlowEngineEntity entity, List<FlowEngineVisibleEntity> visibleList) {
		entity.setVisibleType(visibleList.size() == 0 ? 0 : 1);
		this.save(entity);
		for (FlowEngineVisibleEntity flowEngineVisibleEntity : visibleList) {
			flowEngineVisibleEntity.setId(null);
			flowEngineVisibleEntity.setFlowId(entity.getId());
			flowEngineVisibleEntity.setSort(RandomUtil.parses());
			flowEngineVisibleService.save(flowEngineVisibleEntity);
		}
	}

	@Override
	public boolean update(Long id, FlowEngineEntity entity, List<FlowEngineVisibleEntity> visibleList) {
		entity.setId(id);
		entity.setVisibleType(visibleList.size() == 0 ? 0 : 1);
		boolean flag = this.updateById(entity);
		if (flag) {
			QueryWrapper<FlowEngineVisibleEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FlowEngineVisibleEntity::getFlowId, entity.getId());
			flowEngineVisibleService.remove(queryWrapper);
			for (int i = 0; i < visibleList.size(); i++) {
				visibleList.get(i).setFlowId(entity.getId());
				visibleList.get(i).setSort(i);
				flowEngineVisibleService.save(visibleList.get(i));
			}
		}
		return flag;
	}

	@Override
	public void update(Long id, FlowEngineEntity entity) {
		entity.setId(id);
		this.updateById(entity);
	}

	@Override
	public boolean first(Long id) {
		boolean isOk = false;
		//获取要上移的那条数据的信息
		FlowEngineEntity upEntity = this.getById(id);
		Integer upSortCode = upEntity.getSort() == null ? 0 : upEntity.getSort();
		//查询上几条记录
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda()
				.lt(FlowEngineEntity::getSort, upSortCode)
				.eq(FlowEngineEntity::getCategory, upEntity.getCategory())
				.orderByDesc(FlowEngineEntity::getSort);
		List<FlowEngineEntity> downEntity = this.list(queryWrapper);
		if (downEntity.size() > 0) {
			//交换两条记录的sort值
			Integer temp = upEntity.getSort();
			upEntity.setSort(downEntity.get(0).getSort());
			downEntity.get(0).setSort(temp);
			updateById(downEntity.get(0));
			updateById(upEntity);
			isOk = true;
		}
		return isOk;
	}

	@Override
	public boolean next(Long id) {
		boolean isOk = false;
		//获取要下移的那条数据的信息
		FlowEngineEntity downEntity = this.getById(id);
		Integer upSortCode = downEntity.getSort() == null ? 0 : downEntity.getSort();
		//查询下几条记录
		QueryWrapper<FlowEngineEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda()
				.gt(FlowEngineEntity::getSort, upSortCode)
				.eq(FlowEngineEntity::getCategory, downEntity.getCategory())
				.orderByAsc(FlowEngineEntity::getSort);
		List<FlowEngineEntity> upEntity = this.list(queryWrapper);
		if (upEntity.size() > 0) {
			//交换两条记录的sort值
			Integer temp = downEntity.getSort();
			downEntity.setSort(upEntity.get(0).getSort());
			upEntity.get(0).setSort(temp);
			updateById(upEntity.get(0));
			updateById(downEntity);
			isOk = true;
		}
		return isOk;
	}

	@Override
	public long getFlowNodeList(String stepId, List<FlowTaskNodeEntity> flowTaskNodeList) {
		long freeApprover = 0;
		if (StrUtil.isNotEmpty(stepId)) {
			String[] idAll = stepId.split(",");
			for (String id : idAll) {
				//当前节点是加签
				FlowTaskNodeEntity thisStep = flowTaskNodeList.stream().filter(t -> t.getNodeCode().equals(id)).findFirst().orElse(null);
				if (thisStep != null) {
					ChildNodeList thisNode = JsonUtil.getJsonToBean(thisStep.getNodePropertyJson(), ChildNodeList.class);
					String type = String.valueOf(thisNode.getProperties().getAssigneeType());
					if (type.equals(String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()))) {
						freeApprover = 1;
						break;
					} else {
						//下个节点是加签
						if (thisStep.getNodeNext() != null) {
							String[] nextAll = thisStep.getNodeNext().split(",");
							for (String nextId : nextAll) {
								FlowTaskNodeEntity nextStep = flowTaskNodeList.stream().filter(t -> t.getNodeCode().equals(nextId)).findFirst().orElse(null);
								if (nextStep != null) {
									ChildNodeList nextNode = JsonUtil.getJsonToBean(nextStep.getNodePropertyJson(), ChildNodeList.class);
									String nexttype = String.valueOf(nextNode.getProperties().getAssigneeType());
									if (nexttype.equals(String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()))) {
										freeApprover = 1;
										break;
									}
								}
							}
							//跳出循环
							if (freeApprover == 1) {
								break;
							}
						}
					}
				}
			}
		}
		return freeApprover;
	}
}
