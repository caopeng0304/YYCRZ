package com.sinosoft.ie.booster.workflow.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * 配置OAuthClient相关的Bean
 *
 * @author haocy
 * @since 2022-03-25
 */
@Configuration
public class OAuthClientConfiguration {

	@Bean
	@LoadBalanced
	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}
}
