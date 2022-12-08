package com.sinosoft.ie.booster.admin;

import com.sinosoft.ie.booster.common.feign.annotation.EnableBoosterFeignClients;
import com.sinosoft.ie.booster.common.security.annotation.EnableBoosterResourceServer;
import com.sinosoft.ie.booster.common.swagger.annotation.EnableBoosterSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lengleng
 * @since 2018年06月21日 用户统一管理系统
 */
@EnableBoosterSwagger2
@EnableBoosterResourceServer
@EnableBoosterFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UpmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpmsApplication.class, args);
	}

}
