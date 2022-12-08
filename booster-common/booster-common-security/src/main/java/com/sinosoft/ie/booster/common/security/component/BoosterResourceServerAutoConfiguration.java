package com.sinosoft.ie.booster.common.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author lengleng
 * @since 2020-06-23
 */
@EnableConfigurationProperties(PermitAllUrlProperties.class)
public class BoosterResourceServerAutoConfiguration {

	@Bean("pms")
	public PermissionService permissionService() {
		return new PermissionService();
	}

	@Bean
	public BoosterAccessDeniedHandler boosterAccessDeniedHandler(ObjectMapper objectMapper) {
		return new BoosterAccessDeniedHandler(objectMapper);
	}

	@Bean
	public BoosterBearerTokenExtractor boosterBearerTokenExtractor(PermitAllUrlProperties urlProperties) {
		return new BoosterBearerTokenExtractor(urlProperties);
	}

	@Bean
	public ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint(ObjectMapper objectMapper) {
		return new ResourceAuthExceptionEntryPoint(objectMapper);
	}

	@Bean
	@Primary
	@LoadBalanced
	public RestTemplate lbRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		// 传递ACCEPT JSON
		restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
			request.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			return execution.execute(request, body);
		}));

		// 处理400 异常
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			@SneakyThrows
			public void handleError(ClientHttpResponse response) {
				// 当认证中心返回 400 或者 424 错误码不抛异常，交给资源服务自行处理
				if (response.getRawStatusCode() == HttpStatus.FAILED_DEPENDENCY.value()
						|| response.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()) {
					return;
				}

				// 原有异常处理逻辑
				super.handleError(response);
			}
		});
		return restTemplate;
	}

}
