package com.sinosoft.ie.booster.auth;

import com.sinosoft.ie.booster.common.feign.annotation.EnableBoosterFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 认证授权中心
 *
 * @author lengleng
 * @since 2018-06-21
 */
@EnableBoosterFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
