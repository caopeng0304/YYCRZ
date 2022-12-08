/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sinosoft.ie.booster.mqroute.config;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.MixAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.apache.rocketmq.client.ClientConfig.SEND_MESSAGE_WITH_VIP_CHANNEL_PROPERTY;

@Configuration
@PropertySource("classpath:application-rocket.properties")
@ConfigurationProperties(prefix = "rocketmq")
public class RocketConfig {

	private final Logger logger = LoggerFactory.getLogger(RocketConfig.class);

	private volatile String namesrvAddr = System.getProperty(MixAll.NAMESRV_ADDR_PROPERTY,
			System.getenv(MixAll.NAMESRV_ADDR_ENV));

	private volatile String isVIPChannel = System.getProperty(SEND_MESSAGE_WITH_VIP_CHANNEL_PROPERTY, "true");

	private boolean enableDashBoardCollect;

	private boolean loginRequired = false;

	private String accessKey;

	private String secretKey;

	private boolean useTLS = false;

	/* 超时时间 */
	@Value("${rocketmq.producer.sendMsgTimeOut}")
	private Integer timeoutMillis;
	
	//@Value("${rocketmq.name-server}")
	private String name_server;
	public void setName_server(String name_server) {
		this.name_server = name_server;
		setNamesrvAddrs();
	}
	private List<String> namesrvAddrs ;

	/* nameserver地址 */
	private Set<String> nameSetAddrs;

	/* broker名称，地址 */
	private String brokerName;
	private String brokerAddrs;

	private String producerGroup;
	
	@Value("${rocketmq.consumer.group}")
	private String consumerGroup;

	public String getProducerGroup() {
		return producerGroup;
	}

	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getName_server() {
		setNamesrvAddrs();
		return name_server;
	}
	
	public String getNamesrvAddr() {
		if (StringUtils.isEmpty(namesrvAddr)) {
			setNamesrvAddrs();
			this.namesrvAddr = namesrvAddrs.get(0);
			System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, namesrvAddr);
		}
		return namesrvAddr;
	}

	public void setNamesrvAddrs() {
		if (CollectionUtils.isEmpty(namesrvAddrs)) {
			String[] arr = name_server.split(";");
			namesrvAddrs = new ArrayList<>();
			Collections.addAll(namesrvAddrs, arr);
			setNamesrvAddr(arr[0]);
		}
	}
	
	public List<String> getNamesrvAddrs() {
		return namesrvAddrs;
	}


	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
		System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, namesrvAddr);
		logger.info("RocketMq---->setNameSrvAddrByProperty nameSrvAddr={}", namesrvAddr);
	}

	public boolean isACLEnabled() {
		return !(StringUtils.isAnyBlank(this.accessKey, this.secretKey)
				|| StringUtils.isAnyEmpty(this.accessKey, this.secretKey));
	}

	public String getIsVIPChannel() {
		return isVIPChannel;
	}

	public void setIsVIPChannel(String isVIPChannel) {
		if (StringUtils.isNotBlank(isVIPChannel)) {
			this.isVIPChannel = isVIPChannel;
			System.setProperty(SEND_MESSAGE_WITH_VIP_CHANNEL_PROPERTY, isVIPChannel);
			logger.info("setIsVIPChannel isVIPChannel={}", isVIPChannel);
		}
	}

	public boolean isEnableDashBoardCollect() {
		return enableDashBoardCollect;
	}

	public void setEnableDashBoardCollect(String enableDashBoardCollect) {
		this.enableDashBoardCollect = Boolean.parseBoolean(enableDashBoardCollect);
	}

	public boolean isLoginRequired() {
		return loginRequired;
	}

	public void setLoginRequired(boolean loginRequired) {
		this.loginRequired = loginRequired;
	}

	public boolean isUseTLS() {
		return useTLS;
	}

	public void setUseTLS(boolean useTLS) {
		this.useTLS = useTLS;
	}

	public Integer getTimeoutMillis() {
		return timeoutMillis;
	}

	public void setTimeoutMillis(Integer timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getBrokerAddrs() {
		return brokerAddrs;
	}

	public void setBrokerAddrs(String brokerAddrs) {
		this.brokerAddrs = brokerAddrs;
	}

	// Error Page process logic, move to a central configure later
	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return new MyErrorPageRegistrar();
	}

	public Set<String> getNameSetArrs() {
		if (nameSetAddrs == null) {
			if (StringUtils.isEmpty(namesrvAddr)) {
				setNamesrvAddrs();
			}
			this.nameSetAddrs = new HashSet<>(namesrvAddrs);
		}
		return nameSetAddrs;
	}

	private static class MyErrorPageRegistrar implements ErrorPageRegistrar {

		@Override
		public void registerErrorPages(ErrorPageRegistry registry) {
			registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
		}

	}
}
