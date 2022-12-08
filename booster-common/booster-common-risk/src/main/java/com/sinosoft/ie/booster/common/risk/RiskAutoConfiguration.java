package com.sinosoft.ie.booster.common.risk;

import com.sinosoft.ie.booster.common.risk.filter.RiskFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The configuration for risk inspection beans
 *
 * @author haocy
 * @since 2022-11-24
 */
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "risk.upload-url")
@EnableConfigurationProperties(RiskProperties.class)
public class RiskAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(RiskFilter.class)
	public RiskFilter riskFilter(@Qualifier("restTemplate") RestTemplate restTemplate, RiskProperties riskProperties) {
		return new RiskFilter(restTemplate, riskProperties);
	}

	@Bean
	public FilterRegistrationBean<RiskFilter> riskFilterRegistration(RiskFilter riskFilter) {
		FilterRegistrationBean<RiskFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(riskFilter);
		registration.addUrlPatterns("/*");
		registration.setName("riskFilter");
		registration.setOrder(0);
		return registration;
	}
}
