package com.sinosoft.ie.booster.mqroute.topic;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.sinosoft.ie.booster.mqroute.config.KafkaConfig;
import com.sinosoft.ie.booster.mqroute.console.Assembly;

/**
 * 动态注入bean 根据配置文件中的<spring.profiles.include>参数动态注入bean
 * 
 * @author gt
 *
 */
@Configuration
public class TopicBean {

	@Conditional({ KafkaTopicCondition.class })
	@Bean
	public KafkaTopicHandle kafkaTopicHandle() {
		return new KafkaTopicHandle();
	}

	@Conditional({ RocketTopicCondition.class })
	@Bean
	public RocketTopicHandle rocketTopicHandle() {
		return new RocketTopicHandle();
	}

}

class RocketTopicCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String active = context.getEnvironment().getProperty("spring.profiles.include");
		return Assembly.ROCKET.equals(active);
	}

}

class KafkaTopicCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String active = context.getEnvironment().getProperty("spring.profiles.include");
		return Assembly.KAFKA.equals(active);
	}

}
