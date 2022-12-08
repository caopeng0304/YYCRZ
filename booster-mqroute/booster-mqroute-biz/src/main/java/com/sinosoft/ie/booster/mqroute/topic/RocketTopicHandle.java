package com.sinosoft.ie.booster.mqroute.topic;

import com.sinosoft.ie.booster.mqroute.config.RocketConfig;
import com.sinosoft.ie.booster.mqroute.err.MqError;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RocketTopicHandle implements TopicHandle {

	public RocketTopicHandle() {
		System.out.println("注册了bean-->RocketTopicHandle");
	}

	@Autowired
	private RocketConfig rocketConfig;
	@Autowired
	private DefaultMQAdminExt defaultMQAdminExt;

	@Override
	public void createTopic(String name) {
		try {
			defaultMQAdminExt.createTopic("broker-a", name, 8);
		} catch (MQClientException e) {
			log.error("MQClientException", e);
			throw new MqError("新增主题出错--Rocket");
		}
	}

	@Override
	public void deleteTopic(String name) {
		try {
			defaultMQAdminExt.deleteTopicInNameServer(rocketConfig.getNameSetArrs(), name);
		} catch (RemotingException | MQBrokerException | InterruptedException e) {
			e.printStackTrace();
		} catch (MQClientException e) {
			log.error("MQClientException", e);
			throw new MqError("删除主题出错--Rocket");
		}
	}

}
