package com.sinosoft.ie.booster.mqroute.service.impl;

import java.util.List;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.mqroute.cache.MqCache;
import com.sinosoft.ie.booster.mqroute.config.RocketConfig;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import com.sinosoft.ie.booster.mqroute.err.MqError;
import com.sinosoft.ie.booster.mqroute.init.ConsumerInit;
import com.sinosoft.ie.booster.mqroute.mapper.TopicMapper;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicPagination;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicUpForm;
import com.sinosoft.ie.booster.mqroute.service.ConsumerService;
import com.sinosoft.ie.booster.mqroute.service.TopicService;
import com.sinosoft.ie.booster.mqroute.topic.TopicHandle;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 消息队列组件 版本： V1.0.0 作者： booster开发平台组 日期： 2022-03-15 15:12:51
 */
@Slf4j
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, TopicEntity> implements TopicService {

	// @Autowired 
	// private GenericObjectPool<MQAdminExt> mqAdminExtObjectPool;
	@Autowired
	private RocketConfig rMQConfigure;
	@Autowired
	private DefaultMQAdminExt defaultMQAdminExt;
	@Autowired
	private ConsumerInit consumerInit;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private TopicHandle topicHandle;

	@Override
	public List<TopicEntity> getList(TopicPagination topicPagination) {
		QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
		if (!"null".equals(String.valueOf(topicPagination.getId()))) {
			queryWrapper.lambda().and(t -> t.like(TopicEntity::getId, topicPagination.getId()));
		}

		// 排序
		if (StrUtil.isEmpty(topicPagination.getSidx())) {
			queryWrapper.lambda().orderByDesc(TopicEntity::getId);
		} else {
			queryWrapper = "asc".equals(topicPagination.getSort().toLowerCase())
					? queryWrapper.orderByAsc(topicPagination.getSidx())
					: queryWrapper.orderByDesc(topicPagination.getSidx());
		}
		Page<TopicEntity> page = new Page<>(topicPagination.getCurrentPage(), topicPagination.getPageSize());
		IPage<TopicEntity> userIPage = this.page(page, queryWrapper);
		return topicPagination.setData(userIPage.getRecords(), userIPage.getTotal());
	}

	@Override
	public List<TopicEntity> getTypeList(TopicPagination topicPagination, String dataType) {
		QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
		if (!"null".equals(String.valueOf(topicPagination.getId()))) {
			queryWrapper.lambda().and(t -> t.like(TopicEntity::getId, topicPagination.getId()));
		}
		// 排序
		if (StrUtil.isEmpty(topicPagination.getSidx())) {
			queryWrapper.lambda().orderByDesc(TopicEntity::getId);
		} else {
			queryWrapper = "asc".equals(topicPagination.getSort().toLowerCase())
					? queryWrapper.orderByAsc(topicPagination.getSidx())
					: queryWrapper.orderByDesc(topicPagination.getSidx());
		}
		if ("0".equals(dataType)) {
			Page<TopicEntity> page = new Page<>(topicPagination.getCurrentPage(), topicPagination.getPageSize());
			IPage<TopicEntity> userIPage = this.page(page, queryWrapper);
			return topicPagination.setData(userIPage.getRecords(), userIPage.getTotal());
		} else {
			return this.list(queryWrapper);
		}
	}

	@Override
	public TopicEntity getInfo(Long id) {
		QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(TopicEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public R create(TopicEntity entity) {
		topicHandle.createTopic(entity.getTopicname());
		QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(TopicEntity::getTopicname, entity.getTopicname());
		queryWrapper.last("LIMIT 1");
		TopicEntity ent = this.getOne(queryWrapper);
		if (ent != null) {
			return R.failed("主题名称已存在，请重新命名！");
		}
		this.save(entity);
		consumerInit.resetConsumer();
		return R.ok(null, "新建成功");

	}

	@Override
	public boolean update(Long id, TopicEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(TopicEntity entity) {
		if (entity != null) {
			QueryWrapper<ConsumerEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("id", entity.getId());
			queryWrapper.last("LIMIT 1");
			ConsumerEntity ent = consumerService.getOne(queryWrapper);
			if (ent != null) {
				throw new MqError("该主题正在使用，不可删除！");
			}
			//consumerService.query();
			// defaultMQAdminExt.deleteTopicInNameServer(rMQConfigure.getSetArrs(),
			// entity.getTopicname());
			topicHandle.deleteTopic(entity.getTopicname());
			consumerInit.resetConsumer();
			this.removeById(entity.getId());
		}
	}

	@Override
	public R updateSatae(TopicUpForm topicUpForm) {
		TopicEntity ent = this.getInfo(topicUpForm.getId());
		if (ent == null) {
			return R.failed("当前数据不存在，请重新选择！");
		}
		ent.setState(topicUpForm.getState());
		this.update(topicUpForm.getId(), ent);
		consumerInit.resetConsumer();
		return R.ok();
	}

}