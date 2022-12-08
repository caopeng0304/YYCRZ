package com.sinosoft.ie.booster.common.security.feign;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.commons.security.AccessTokenContextRelay;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * feign拦截器传递header中oauth token，使用hystrix的信号量模式
 *
 * @author lengleng
 * @since 2019/2/1
 */
@ConditionalOnProperty("security.oauth2.client.client-id")
public class BoosterFeignClientConfiguration {

	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
			OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
		return new BoosterFeignClientInterceptor(oAuth2ClientContext, resource, accessTokenContextRelay);
	}

}
