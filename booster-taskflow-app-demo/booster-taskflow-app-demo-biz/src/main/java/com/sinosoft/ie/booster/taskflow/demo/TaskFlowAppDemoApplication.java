package com.sinosoft.ie.booster.taskflow.demo;

import com.sinosoft.ie.booster.common.feign.annotation.EnableBoosterFeignClients;
import com.sinosoft.ie.booster.common.security.annotation.EnableBoosterResourceServer;
import com.sinosoft.ie.booster.common.swagger.annotation.EnableBoosterSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
public class TaskFlowAppDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskFlowAppDemoApplication.class, args);
	}
}
