package com.sinosoft.ie.booster.visualdev.util;

import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:47
 */
@Data
@Component
public class DataSourceUtil {

	/**
	 * 数据库类型
	 */
	private String dataType;

	//-----------------------------------数据配置
	/**
	 * 驱动包
	 */
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	/**
	 * 数据连接字符串
	 */
	@Value("${spring.datasource.url}")
	private String url;
	/**
	 * 账号
	 */
	@Value("${spring.datasource.username}")
	private String userName;
	/**
	 * 密码
	 */
	@Value("${spring.datasource.password}")
	private String password;

	//----------Oracle表空间(暂无)
	/**
	 * 密码
	 */
	@Value("${spring.tableSpace:BOOSTERCLOUD}")
	private String tableSpace;

	public String getDataType() {
		DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDriverClassName(driverClassName);
		Assert.notNull(dataSourceEnum, "datasource is null");
		return dataSourceEnum.getDbLinkType();
	}
}
