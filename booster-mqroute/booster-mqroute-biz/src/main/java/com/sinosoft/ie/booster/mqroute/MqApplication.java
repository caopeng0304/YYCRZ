package com.sinosoft.ie.booster.mqroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.sinosoft.ie.booster.common.feign.annotation.EnableBoosterFeignClients;
import com.sinosoft.ie.booster.common.security.annotation.EnableBoosterResourceServer;
import com.sinosoft.ie.booster.common.swagger.annotation.EnableBoosterSwagger2;

/**
 * @author booster
 *
 * 项目启动类
 */
@EnableBoosterSwagger2
@EnableBoosterFeignClients
@EnableBoosterResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class MqApplication {
	public static void main(String[] args) {
		SpringApplication.run(MqApplication.class, args);
		System.out.println("Success!");
	}

}
