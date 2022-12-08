package com.sinosoft.ie.booster.common.security.annotation;

import com.sinosoft.ie.booster.common.security.component.BoosterResourceServerAutoConfiguration;
import com.sinosoft.ie.booster.common.security.component.BoosterResourceServerTokenRelayAutoConfiguration;
import com.sinosoft.ie.booster.common.security.component.BoosterSecurityBeanDefinitionRegistrar;
import com.sinosoft.ie.booster.common.security.feign.BoosterFeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author lengleng
 * @since 2019/03/08
 * <p>
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ BoosterResourceServerAutoConfiguration.class, BoosterSecurityBeanDefinitionRegistrar.class,
		BoosterResourceServerTokenRelayAutoConfiguration.class, BoosterFeignClientConfiguration.class })
public @interface EnableBoosterResourceServer {

}
