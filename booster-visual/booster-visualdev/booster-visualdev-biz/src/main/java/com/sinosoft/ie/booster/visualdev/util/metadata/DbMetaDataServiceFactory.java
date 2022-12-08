package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.NOT_SUPPORTED;

/**
 * 数据库元数据服务工厂类
 *
 * @author haocy
 * @since 2022-03-31
 */
public class DbMetaDataServiceFactory {

	private final String driverClassName;
	private final String username;
	private final String password;
	private final String jdbcUrl;

	private final Map<String, Class<? extends DbMetaDataService>> dbMetaDataServiceMap;

	/**
	 * 构造函数
	 *
	 * @param driverClassName 驱动类名
	 * @param username        用户名
	 * @param password        密码
	 * @param jdbcUrl         url
	 */
	public DbMetaDataServiceFactory(String driverClassName, String username, String password, String jdbcUrl) {
		this.driverClassName = driverClassName;
		this.username = username;
		this.password = password;
		this.jdbcUrl = jdbcUrl;

		//初始化dbMetaDataServiceMap
		dbMetaDataServiceMap = new HashMap<>();
		dbMetaDataServiceMap.put(DataSourceEnum.MYSQL.getDriverClassName(), MySqlMetaDataServiceImpl.class);
		dbMetaDataServiceMap.put(DataSourceEnum.POSTGRE_SQL.getDriverClassName(), PostgreSqlMetaDataServiceImpl.class);
		dbMetaDataServiceMap.put(DataSourceEnum.KINGBASE_ES.getDriverClassName(), OtherMetaDataServiceImpl.class);
		dbMetaDataServiceMap.put(DataSourceEnum.DM.getDriverClassName(), DmMetaDataServiceImpl.class);
		dbMetaDataServiceMap.put(DataSourceEnum.ORACLE.getDriverClassName(), OracleMetaDataServiceImpl.class);
		dbMetaDataServiceMap.put(DataSourceEnum.SQL_SERVER.getDriverClassName(), SqlServerMetaDataServiceImpl.class);
	}

	/**
	 * 获取配置的数据库元数据服务接口实例
	 *
	 * @return 数据库元数据服务接口实例
	 */
	public DbMetaDataService newInstance() {
		try {
			Class<? extends DbMetaDataService> dbMetaDataServiceClass = dbMetaDataServiceMap
					.getOrDefault(this.driverClassName, OtherMetaDataServiceImpl.class);
			//获取有参构造
			Constructor<? extends DbMetaDataService> constructor = dbMetaDataServiceClass
					.getConstructor(String.class, String.class, String.class, String.class);
			//实例化
			return constructor.newInstance(this.driverClassName, this.username, this.password, this.jdbcUrl);

		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
			throw ExceptionUtils.mpe(e);
		} catch (InvocationTargetException e) {
			throw ExceptionUtils.mpe(NOT_SUPPORTED);
		}
	}
}
