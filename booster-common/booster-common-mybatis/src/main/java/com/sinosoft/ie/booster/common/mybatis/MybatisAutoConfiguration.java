package com.sinosoft.ie.booster.common.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.mybatis.config.MybatisPlusMetaObjectHandler;
import com.sinosoft.ie.booster.common.mybatis.datascope.DataPermissionHandlerImpl;
import com.sinosoft.ie.booster.common.mybatis.resolver.SqlFilterArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author lengleng
 * @since 2020-03-14
 * <p>
 * mybatis plus 统一配置
 */
@Configuration(proxyBeanMethods = false)
public class MybatisAutoConfiguration implements WebMvcConfigurer {

	/**
	 * 驱动包
	 */
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	/**
	 * SQL 过滤器避免SQL 注入
	 *
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SqlFilterArgumentResolver());
	}

	/**
	 * MybatisPlus插件配置
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(DataSource dataSource) {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 添加数据权限插件
		DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
		DataPermissionHandlerImpl dataPermissionHandler = new DataPermissionHandlerImpl(dataSource);
		// 添加自定义的数据权限处理器
		dataPermissionInterceptor.setDataPermissionHandler(dataPermissionHandler);
		interceptor.addInnerInterceptor(dataPermissionInterceptor);
		//添加分页插件，对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
		DbType dbType = DbType.MYSQL;
		DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDriverClassName(driverClassName);
		if (dataSourceEnum != null) {
			dbType = dataSourceEnum.getDbType();
		}
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(dbType));
		return interceptor;
	}

	/**
	 * 审计字段自动填充
	 * @return {@link MybatisPlusMetaObjectHandler}
	 */
	@Bean
	public MybatisPlusMetaObjectHandler mybatisPlusMetaObjectHandler() {
		return new MybatisPlusMetaObjectHandler();
	}

}
