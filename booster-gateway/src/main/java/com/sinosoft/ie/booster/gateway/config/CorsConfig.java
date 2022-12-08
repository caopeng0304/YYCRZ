package com.sinosoft.ie.booster.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 跨域问题
 *
 * @author booster开发平台组
 * @since 2021-03-24
 */
@Configuration
public class CorsConfig {
	private static final String ALL = "*";

	@Bean
	public CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedMethod(ALL);
		config.addAllowedOrigin(ALL);
		config.addAllowedHeader(ALL);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(source);
	}

}
