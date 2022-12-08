package com.sinosoft.ie.booster.workflow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskNodeEntity;
import com.sinosoft.ie.booster.workflow.mapper.FlowTaskNodeMapper;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.ChildNodeList;
import com.sinosoft.ie.booster.workflow.service.FlowTaskNodeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程节点
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowTaskNodeServiceImpl extends ServiceImpl<FlowTaskNodeMapper, FlowTaskNodeEntity> implements FlowTaskNodeService {

	@Override
	public List<FlowTaskNodeEntity> getListAll() {
		QueryWrapper<FlowTaskNodeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByAsc(FlowTaskNodeEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public List<FlowTaskNodeEntity> getList(Long taskId) {
		QueryWrapper<FlowTaskNodeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskNodeEntity::getTaskId, taskId).orderByAsc(FlowTaskNodeEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public FlowTaskNodeEntity getInfo(Long id) {
		QueryWrapper<FlowTaskNodeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskNodeEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void deleteByTaskId(Long taskId) {
		QueryWrapper<FlowTaskNodeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskNodeEntity::getTaskId, taskId);
		this.remove(queryWrapper);
	}

	@Override
	public void create(List<FlowTaskNodeEntity> dataAll) {
		List<FlowTaskNodeEntity> startNodes = dataAll.stream().filter(t -> "start".equals(t.getNodeType())).collect(Collectors.toList());
		if (startNodes.size() > 0) {
			String startNode = startNodes.get(0).getNodeCode();
			int num = 0;
			Integer maxNum = 0;
			List<Integer> max = new ArrayList<>();
			List<FlowTaskNodeEntity> treeList = new ArrayList<>();
			nodeList(dataAll, startNode, treeList, num, max);
			List<Integer> sortIdList = max.stream().sorted(Integer::compareTo).collect(Collectors.toList());
			if (sortIdList.size() > 0) {
				maxNum = sortIdList.get(sortIdList.size() - 1);
			}
			String nodeNext = "end";
			for (FlowTaskNodeEntity entity : dataAll) {
				String type = entity.getNodeType();
				FlowTaskNodeEntity node = treeList.stream().filter(t -> t.getNodeCode().equals(entity.getNodeCode())).findFirst().orElse(null);
				//判断结束节点是否多个
				List<FlowTaskNodeEntity> endCount = treeList.stream().filter(t -> StrUtil.isEmpty(t.getNodeNext())).collect(Collectors.toList());
				//判断下一节点是否多个
				String next = entity.getNodeNext();
				List<FlowTaskNodeEntity> nextNum = treeList.stream().filter(t -> t.getNodeNext().equals(next)).collect(Collectors.toList());
				if (StrUtil.isEmpty(next)) {
					entity.setNodeNext(nodeNext);
				}
				if (node != null) {
					entity.setSort(node.getSort());
					entity.setState("0");
					if (StrUtil.isEmpty(next)) {
						entity.setNodeNext(nodeNext);
					}
				}
				//判断下一节点是否相同
				if (!"empty".equals(type) && !"timer".equals(type)) {
					//至少2条下一节点一样,才有可能是分流
					if (endCount.size() > 1) {
						if (nodeNext.equals(entity.getNodeNext())) {
							ChildNodeList modelList = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
							//添加指向下一节点的id
							List<String> nextEndList = endCount.stream().map(FlowTaskNodeEntity::getNodeCode).collect(Collectors.toList());
							nextEndList.remove(entity.getNodeCode());
							//赋值合流id和分流的id
							modelList.getCustom().setInterflow(true);
							modelList.getCustom().setInterflowId(String.join(",", nextEndList));
							modelList.getCustom().setInterflowNextId(nodeNext);
							entity.setNodePropertyJson(JsonUtilEx.getObjectToString(modelList));
						}
					}
					//至少2条下一节点一样,才有可能是分流
					if (nextNum.size() > 1) {
						ChildNodeList modelList = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
						//添加指向下一节点的id
						List<String> nextEndList = nextNum.stream().map(FlowTaskNodeEntity::getNodeCode).collect(Collectors.toList());
						nextEndList.remove(entity.getNodeCode());
						//赋值合流id和分流的id
						modelList.getCustom().setInterflowId(String.join(",", nextEndList));
						modelList.getCustom().setInterflowNextId(next);
						modelList.getCustom().setInterflow(true);
						entity.setNodePropertyJson(JsonUtilEx.getObjectToString(modelList));
					}
					this.save(entity);
				}
			}
			FlowTaskNodeEntity endround = new FlowTaskNodeEntity();
			endround.setNodeCode(nodeNext);
			endround.setNodeName("结束");
			endround.setCompletion(0);
			endround.setCreateTime(LocalDateTime.now());
			endround.setSort(maxNum + 1);
			endround.setTaskId(treeList.get(0).getTaskId());
			endround.setNodePropertyJson(startNodes.get(0).getNodePropertyJson());
			endround.setNodeType("endround");
			endround.setState("0");
			this.save(endround);
		}
	}

	@Override
	public void update(FlowTaskNodeEntity entity) {
		this.updateById(entity);
	}

	@Override
	public void update(Long taskId) {
		this.baseMapper.updateState(taskId);
	}


	private void nodeList(List<FlowTaskNodeEntity> dataAll, String nodeCode, List<FlowTaskNodeEntity> treeList, int num, List<Integer> max) {
		num++;
		max.add(num);
		List<FlowTaskNodeEntity> thisEntity = dataAll.stream().filter(t -> t.getNodeCode().contains(nodeCode)).collect(Collectors.toList());
		for (FlowTaskNodeEntity entity : thisEntity) {
			entity.setSort(num);
			entity.setState("0");
			treeList.add(entity);
			String[] nodeNext = entity.getNodeNext().split(",");
			if (nodeNext.length > 0) {
				for (String next : nodeNext) {
					long nums = treeList.stream().filter(t -> t.getNodeCode().equals(next)).count();
					if (StrUtil.isNotEmpty(next) && nums == 0) {
						nodeList(dataAll, next, treeList, num, max);
					}
				}
			}
		}
	}
}
