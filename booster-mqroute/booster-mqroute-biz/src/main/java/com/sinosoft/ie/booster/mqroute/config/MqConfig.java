package com.sinosoft.ie.booster.mqroute.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MqConfig {
	
    @Value("${spring.profiles.include}")
    private String mq_active;
    
    public String getMq_active() {
		return mq_active;
	}

	public void setMq_active(String mq_active) {
		this.mq_active = mq_active;
	}

}
