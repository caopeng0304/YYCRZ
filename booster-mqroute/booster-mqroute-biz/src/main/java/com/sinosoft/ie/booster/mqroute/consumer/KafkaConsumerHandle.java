package com.sinosoft.ie.booster.mqroute.consumer;

import com.sinosoft.ie.booster.mqroute.cache.MqCache;
import com.sinosoft.ie.booster.mqroute.config.KafkaConfig;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Slf4j
public class KafkaConsumerHandle implements ConsumerHandle, Runnable {

	private Thread thread;

	@Autowired
	private RestTemplate restTemplate;

	private static KafkaConsumer<String, String> consumer;

	public KafkaConsumer<String, String> getInitConsumer(KafkaConfig kafkaConfig) {
		// 配置信息
		Properties props = new Properties();
		// kafka服务器地址
		props.put("bootstrap.servers", kafkaConfig.getServers());
		// 必须指定消费者组
		props.put("group.id", kafkaConfig.getGroupName());
		props.put("enable.auto.commit", "false");
		// 设置数据key和value的序列化处理类
		props.put("key.deserializer", StringDeserializer.class);
		props.put("value.deserializer", StringDeserializer.class);
		// 创建消息者实例
		return new KafkaConsumer<>(props);
	}

	private final KafkaConfig kafkaConfig;
	private final List<TopicEntity> topicList;

	public KafkaConsumerHandle(KafkaConfig kafkaConfig, List<TopicEntity> topicList) {
		this.kafkaConfig = kafkaConfig;
		this.topicList = topicList;
	}


	@Override
	public void run() {
		log.info("创建KafkaConsumerHandle");
		List<String> list = topicList.stream().map(TopicEntity::getTopicname).collect(Collectors.toList());
		consumer = getInitConsumer(kafkaConfig);
		// 订阅topic的消息
		consumer.subscribe(list);

		//创建发送消息组件
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "Bearer " + HttpTool.getTokenStr());
		headers.add("from", "Y");

		try {
			while (true) {
				//	log.info("循环消费");
				boolean fal = thread.isInterrupted();
				if (fal) {
					log.info("正在终止kafka消费线程");
					//通过调用sleep函数使线程报错，退出while
					Thread.sleep(100);
				}
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
				for (ConsumerRecord<String, String> record : records) {
					try {

						System.out.println("topic:" + record.topic() + "" + ",value:" + record.value());

						List<ConsumerEntity> clist = MqCache.getConsumerList(record.topic());
						for (ConsumerEntity cent : clist) {
							MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
							map.add("message", record.value());
							HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
							restTemplate.postForEntity(cent.getCallbackurl(), entity, String.class);
						}
					} catch (Exception e) {
						log.error("消费失败", e);
					}
				}
				//消费成功则提交
				consumer.commitAsync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
			consumer = null;
			log.info("关闭kafkaconsumer");
		}
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void shutdown() {
		thread.interrupt();
		consumer.wakeup();
		boolean fal = thread.isInterrupted();
		log.info("停止当前 kafkaconsumer");
		System.out.println(fal);
	}
}
