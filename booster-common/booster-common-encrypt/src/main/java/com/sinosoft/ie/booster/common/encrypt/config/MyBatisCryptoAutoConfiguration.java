package com.sinosoft.ie.booster.common.encrypt.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dpy
 */
@Configuration(proxyBeanMethods = false)
public class MyBatisCryptoAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(MybatisEncryptionPlugin.class)
	public MybatisEncryptionPlugin encryptionInterceptor() {
		return new MybatisEncryptionPlugin();
	}

	@Bean
	@ConditionalOnMissingBean(MybatisDecryptionPlugin.class)
	public MybatisDecryptionPlugin decryptionInterceptor() {
		return new MybatisDecryptionPlugin();
	}

}
