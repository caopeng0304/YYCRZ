package com.sinosoft.ie.booster.common.swagger.annotation;

import com.sinosoft.ie.booster.common.swagger.config.GatewaySwaggerAutoConfiguration;
import com.sinosoft.ie.booster.common.swagger.config.SwaggerAutoConfiguration;
import com.sinosoft.ie.booster.common.swagger.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 booster swagger
 *
 * @author lengleng
 * @since 2020/10/2
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({SwaggerAutoConfiguration.class, GatewaySwaggerAutoConfiguration.class})
public @interface EnableBoosterSwagger2 {

}
