package com.sinosoft.ie.booster.mqroute.consumer;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.sinosoft.ie.booster.mqroute.cache.MqCache;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class RocketConsumerHandle implements ConsumerHandle {

	private static DefaultMQPushConsumer consumer;
	private final String groupName;
	private final String namesrvAddr;
	private final List<TopicEntity> toplist;

	@Autowired
	private RestTemplate restTemplate;


	//验证消息是否重复，保存15分钟
	TimedCache<String, String> timedCache = CacheUtil.newTimedCache(1000 * 60 * 15);

	public RocketConsumerHandle(String groupName, String namesrvAddr, List<TopicEntity> toplist) {
		this.groupName = groupName;
		this.namesrvAddr = namesrvAddr;
		this.toplist = toplist;
	}

	@Override
	public void start() {
		log.info("创建RocketConsumerThread");
		consumer = new DefaultMQPushConsumer(groupName);
		// 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		// NameServer地址， 多个地址用分号(;)分隔
		consumer.setNamesrvAddr(namesrvAddr);
		// 每次拉取的间隔，单位为毫秒
		consumer.setPullInterval(2000);
		// 设置每次从队列中拉取的消息数为16
		consumer.setPullBatchSize(16);
		//创建发送消息组件
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "Bearer " + HttpTool.getTokenStr());
		headers.add("from", "Y");
		try {
			for (TopicEntity ent : toplist) {
				log.info("注册的主题---->{}", ent.getTopicname());
				// 参数1：topic名字 参数2：tag名字,*表示所有
				consumer.subscribe(ent.getTopicname(), "*");
			}
			consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
				for (MessageExt msg : msgs) {
					if (timedCache.containsKey(msg.getKeys())) {
						continue;
					}
					List<ConsumerEntity> clist = MqCache.getConsumerList(msg.getTopic());
					for (ConsumerEntity cent : clist) {
						log.info("向{}发送主题为{}的消息{}", cent.getConsumename(), msg.getTopic(), new String(msg.getBody()));
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
						map.add("message", msg.getBody());
						HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
						restTemplate.postForEntity(cent.getCallbackurl(), entity, String.class);
					}
					timedCache.put(msg.getKeys(), msg.getTopic());
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			});
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
		log.info("Consumer start  Success!");
	}

	public void shutdown() {
		consumer.shutdown();
	}

}
