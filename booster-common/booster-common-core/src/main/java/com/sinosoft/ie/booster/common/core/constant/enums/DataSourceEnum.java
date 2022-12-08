package com.sinosoft.ie.booster.common.core.constant.enums;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haocy
 * @since 2022/03/16
 * 数据源枚举
 */
@Getter
@AllArgsConstructor
public enum DataSourceEnum {
	/**
	 * MySql数据库
	 */
	MYSQL(DbType.MYSQL, "mysql", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://{host}:{port}/{dbName}?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC"),

	/**
	 * Postgre数据库
	 */
	POSTGRE_SQL(DbType.POSTGRE_SQL, "postgresql", "org.postgresql.Driver", "jdbc:postgresql://{host}:{port}/{dbName}"),

	/**
	 * 人大金仓数据库
	 */
	KINGBASE_ES(DbType.KINGBASE_ES, "kingbase", "com.kingbase8.Driver", "jdbc:kingbase8://{host}:{port}/{dbName}"),

	/**
	 * 达梦数据库
	 */
	DM(DbType.DM, "dm", "dm.jdbc.driver.DmDriver", "jdbc:dm://{host}:{port}"),

	/**
	 * Oracle12c+数据库
	 */
	ORACLE(DbType.ORACLE_12C, "oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@{host}:{port}/{dbName}"),

	/**
	 * SQLServer数据库
	 */
	SQL_SERVER(DbType.SQL_SERVER, "sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://{host}:{port};DatabaseName={dbName}");

	/**
	 * mybatis-plus定义的数据库类型
	 */
	private DbType dbType;

	/**
	 * BaseDbLinkEntity定义的数据库类型
	 */
	private String dbLinkType;

	/**
	 * 数据库驱动类名
	 */
	private String driverClassName;

	/**
	 * 数据库连接模板
	 */
	private String urlTemplate;

	/**
	 * 按数据库驱动类名获取指定数据源
	 *
	 * @param driverClassName 数据库驱动类名
	 */
	public static DataSourceEnum getDataSourceByDriverClassName(String driverClassName) {
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			if (dataSourceEnum.driverClassName.equalsIgnoreCase(driverClassName)) {
				return dataSourceEnum;
			}
		}
		return null;
	}

	/**
	 * 按BaseDbLinkEntity定义的数据库类型获取指定数据源
	 *
	 * @param dbLinkType BaseDbLinkEntity定义的数据库类型
	 */
	public static DataSourceEnum getDataSourceByDbLinkType(String dbLinkType) {
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			if (dataSourceEnum.dbLinkType.equalsIgnoreCase(dbLinkType)) {
				return dataSourceEnum;
			}
		}
		return null;
	}

	/**
	 * 获取DbLinkType列表
	 *
	 * @return DbLinkType列表
	 */
	public static List<String> getDbLinkTypeList() {
		List<String> dbLinkTypeList = new ArrayList<>();
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			dbLinkTypeList.add(dataSourceEnum.getDbLinkType());
		}
		return dbLinkTypeList;
	}
}
