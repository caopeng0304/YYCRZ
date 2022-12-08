package com.sinosoft.ie.booster.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置，配置需要Token验证的url
 *
 * @author haocy
 * @since 2022/02/15
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * 使用此配置安全资源的访问规则，配置需要token验证的url。 默认情况下，所有不在"/oauth/**"中的资源都受到保护。
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 配置需要获取token会被 OAuth2AuthenticationProcessingFilter处理的Url
		http
				.requestMatchers()
				.antMatchers("/user/getUserInfo")
				.and()
				.authorizeRequests()
				.anyRequest().authenticated();
	}

}
