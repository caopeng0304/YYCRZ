package com.sinosoft.ie.booster.mqroute.service.impl;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.mqroute.config.MqConfig;
import com.sinosoft.ie.booster.mqroute.config.RocketConfig;
import com.sinosoft.ie.booster.mqroute.console.Assembly;
import com.sinosoft.ie.booster.mqroute.entity.MqMessage;
import com.sinosoft.ie.booster.mqroute.service.SendService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendServiceImpl implements SendService {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private MqConfig mqConfig;

	private String charset = "UTF-8";

	@Override
	public R sendMsg(MqMessage msg) {
		if (Assembly.ROCKET.equals(mqConfig.getMq_active())) {
			//rocketMQTemplate.convertAndSend(charset, msg);
			try {
				String[] topics = msg.getTopics().split(";");
				for (int i = 0; i < topics.length; i++) {
					rocketMQTemplate.syncSend(topics[i] + ":" + msg.getTag(), msg.getBody());
				}
			} catch (Exception e) {
				log.error("syncSend failed. destination:{}, message:{} ", msg.getTopics(), msg.getBody());
				throw new MessagingException(e.getMessage(), e);
			}
		}if (Assembly.KAFKA.equals(mqConfig.getMq_active())) {
			try {
				String[] topics = msg.getTopics().split(";");
				for (int i = 0; i < topics.length; i++) {
					kafkaTemplate.send(topics[i], msg.getBody());
				}
			} catch (Exception e) {
				throw new MessagingException(e.getMessage(), e);
			}
		}
		return R.ok();
	}

}
