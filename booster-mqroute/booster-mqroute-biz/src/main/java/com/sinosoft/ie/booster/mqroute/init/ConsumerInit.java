package com.sinosoft.ie.booster.mqroute.init;

import java.util.List;

import javax.annotation.Resource;

import com.sinosoft.ie.booster.mqroute.mapper.ConsumerMapper;
import com.sinosoft.ie.booster.mqroute.mapper.ProducerMapper;
import com.sinosoft.ie.booster.mqroute.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.mqroute.cache.MqCache;
import com.sinosoft.ie.booster.mqroute.config.KafkaConfig;
import com.sinosoft.ie.booster.mqroute.config.MqConfig;
import com.sinosoft.ie.booster.mqroute.config.RocketConfig;
import com.sinosoft.ie.booster.mqroute.console.Assembly;
import com.sinosoft.ie.booster.mqroute.consumer.ConsumerHandle;
import com.sinosoft.ie.booster.mqroute.consumer.KafkaConsumerHandle;
import com.sinosoft.ie.booster.mqroute.consumer.RocketConsumerHandle;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.ProducerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ConsumerInit  implements CommandLineRunner{
	
	private  ConsumerHandle consumer;
	@Resource
	private RocketConfig rMQConfigure;
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private ConsumerMapper consumerMapper;
	@Autowired
	private ProducerMapper producerMapper;
	@Autowired
	private MqConfig mqConfig;
	@Autowired
	private KafkaConfig kafkaConfig;
	
	@Override
	public void run(String... args) throws Exception {
		initConsumer();
	}
	
	public void resetConsumer() {
		stopConsumer();
		initConsumer();
	}
	
	
	public void stopConsumer() {
		if(consumer!=null) {
			consumer.shutdown();
			consumer=null;
		}
	}
	
	public void initConsumer() {
		//查询所有状态为可用的主题
		QueryWrapper<TopicEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("state",2);
		List<TopicEntity>  toplist = topicMapper.selectList(queryWrapper);
		if(toplist.size()==0) {
			log.info("没有注册主题");
			return ;
		}
		//查询所有消费者
		QueryWrapper<ConsumerEntity> cqueryWrapper=new QueryWrapper<>();
		cqueryWrapper.eq("state", 2);
		List<ConsumerEntity>  consumerlist = consumerMapper.selectList(cqueryWrapper);
		
		//创建消费者对象
		if( Assembly.ROCKET.equals(mqConfig.getMq_active()) ) {
			consumer =  new RocketConsumerHandle(rMQConfigure.getConsumerGroup(),rMQConfigure.getNamesrvAddr(),toplist);
		}
		if( Assembly.KAFKA.equals(mqConfig.getMq_active()) ) {
			consumer   =  new  KafkaConsumerHandle( kafkaConfig,toplist);
		}
		//启动消费者
		consumer.start();
		QueryWrapper<ProducerEntity> proQuery=new QueryWrapper<>();
		proQuery.eq("state",2);
		List<ProducerEntity>  proList = producerMapper.selectList(proQuery);
		
		
		MqCache.setTopicMap(toplist);
		MqCache.setConsumerListMap(consumerlist);
		
	}

}
