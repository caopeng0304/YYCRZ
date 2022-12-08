package com.sinosoft.ie.booster.mqroute.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



@Configuration
@PropertySource("classpath:application-kafka.properties")
public class KafkaConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String servers;
	@Value("${spring.kafka.consumer.group-id}")
	private String groupName;
	public String getServers() {
		return servers;
	}
	public void setServers(String servers) {
		this.servers = servers;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupNaeme) {
		this.groupName = groupNaeme;
	}

}
