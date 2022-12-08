package com.sinosoft.ie.booster.common.security.component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 1. 支持remoteTokenServices负载均衡
 * 2. 支持获取用户全部信息
 * 3. 接口对外暴露时，不校验Authentication Header
 *
 * @author lengleng
 * @since 2019/03/08
 */
@Slf4j
public class BoosterResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

	@Autowired
	protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

	@Autowired
	protected RemoteTokenServices remoteTokenServices;

	@Autowired
	private AccessDeniedHandler boosterAccessDeniedHandler;

	@Autowired
	private PermitAllUrlProperties permitAllUrl;

	@Autowired
	private RestTemplate lbRestTemplate;

	@Autowired
	private BoosterBearerTokenExtractor boosterBearerTokenExtractor;

	/**
	 * 默认的配置，对外暴露
	 * @param httpSecurity
	 */
	@Override
	@SneakyThrows
	public void configure(HttpSecurity httpSecurity) {
		// 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
		httpSecurity.headers().frameOptions().disable();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
				.authorizeRequests();
		permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
		registry.anyRequest().authenticated().and().csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		UserAuthenticationConverter userTokenConverter = new BoosterUserAuthenticationConverter();
		accessTokenConverter.setUserTokenConverter(userTokenConverter);

		remoteTokenServices.setRestTemplate(lbRestTemplate);
		remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
		resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint).tokenExtractor(boosterBearerTokenExtractor)
				.accessDeniedHandler(boosterAccessDeniedHandler).tokenServices(remoteTokenServices);
	}

}
