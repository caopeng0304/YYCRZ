package com.sinosoft.ie.booster.common.mybatis.datascope;

import java.lang.annotation.*;

/**
 * 数据权限启用标识
 * <p>
 * 支持注解在Mapper.Method上
 * @author haocy
 * @since 2021-07-30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataPermission {
	/**
	 * 指定表别名
	 * @return {String}
	 */
	String tableAlias();
}
