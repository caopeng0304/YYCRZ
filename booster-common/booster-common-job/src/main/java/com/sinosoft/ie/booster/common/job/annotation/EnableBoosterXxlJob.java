package com.sinosoft.ie.booster.common.job.annotation;

import com.sinosoft.ie.booster.common.job.XxlJobAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活xxl-job配置
 *
 * @author lishangbu
 * @since 2020/9/14
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ XxlJobAutoConfiguration.class })
public @interface EnableBoosterXxlJob {

}
