package com.sinosoft.ie.booster.mqroute.topic;

import com.sinosoft.ie.booster.mqroute.config.KafkaConfig;
import com.sinosoft.ie.booster.mqroute.err.MqError;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
public class KafkaTopicHandle implements TopicHandle {

	private AdminClient adminClient;
	@Autowired
	private KafkaConfig kafkaConfig;

	public AdminClient createClient() {
		if (adminClient == null) {
			Properties pro = new Properties();
			pro.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getServers());
			String a = (String) pro.get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG);
			System.out.print(a);
			adminClient = AdminClient.create(pro);
		}
		return adminClient;
	}

	public KafkaTopicHandle() {
		log.info("注册了bean-->KafkaTopicHandle");
	}

	@Override
	public void createTopic(String name) {
		AdminClient adminClient = createClient();
		try {
			log.info("kafka创建topic：{}", name);
			NewTopic topic = new NewTopic(name, 4, (short) 1);
			adminClient.createTopics(Collections.singletonList(topic));
		} catch (Exception e) {
			log.error("kafka创建topic失败", e);
			throw new MqError("kafka创建topic失败");
		}
	}

	/**
	 * 查询所有Topic
	 *
	 * @return
	 * @throws Exception
	 */
	public List<String> list() throws Exception {
		ListTopicsResult listTopicsResult = adminClient.listTopics();
		Set<String> names = listTopicsResult.names().get();
		return new ArrayList<>(names);
	}

	/**
	 * 删除topic
	 *
	 * @param name
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Override
	public void deleteTopic(String name) {
		log.info("kafka删除topic：{}", name);
		AdminClient adminClient = createClient();
		try {
			DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singletonList(name));
			List<String> list = this.list();
			for (String s : list) {
				System.out.println(s);
			}
			deleteTopicsResult.all().get();
		} catch (Exception e) {
			log.error("kafka删除topic失败", e);
			throw new MqError("kafka删除topic失败");
		}
	}

	/**
	 * 获取topic详情
	 *
	 * @param name
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public TopicDescription describeTopic(String name) {
		DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singletonList(name));
		try {
			Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
			if (stringTopicDescriptionMap.get(name) != null) {
				return stringTopicDescriptionMap.get(name);
			}
		} catch (Exception e) {
			log.error(" 获取topic详情异常：", e);
		}
		return null;
	}

}
