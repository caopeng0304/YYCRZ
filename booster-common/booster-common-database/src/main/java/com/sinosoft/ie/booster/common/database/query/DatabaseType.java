package com.sinosoft.ie.booster.common.database.query;

import com.sinosoft.ie.booster.common.database.query.cachedb.CacheDbDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.db2.Db2DataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.dm.DmDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.h2.H2DataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.highgo.HigHgoDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.hsql.HsqlDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.mariadb.MariaDbDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.mysql.MySqlDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.oracle.OracleDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.phoenix.PhoenixDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.postgresql.PostgreSqlDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.sqlite.SqliteDataBaseQuery;
import com.sinosoft.ie.booster.common.database.query.sqlserver.SqlServerDataBaseQuery;
import lombok.Getter;

import java.io.Serializable;

/**
 * 数据库类型
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/18 11:59
 */
public enum DatabaseType implements Serializable {
	/**
	 * MYSQL
	 */
	MYSQL("mysql", "MySql数据库",
			MySqlDataBaseQuery.class),

	/**
	 * MARIA DB
	 */
	MARIADB("mariadb", "MariaDB数据库",
			MariaDbDataBaseQuery.class),

	/**
	 * ORACLE
	 */
	ORACLE("oracle", "Oracle数据库",
			OracleDataBaseQuery.class),

	/**
	 * DB2
	 */
	DB2("db2", "DB2数据库", Db2DataBaseQuery.class),

	/**
	 * H2
	 */
	H2("h2", "H2数据库", H2DataBaseQuery.class),

	/**
	 * HSQL
	 */
	HSQL("hsql", "HSQL数据库", HsqlDataBaseQuery.class),

	/**
	 * SQLITE
	 */
	SQLITE("sqlite", "SQLite数据库",
			SqliteDataBaseQuery.class),

	/**
	 * POSTGRE
	 */
	POSTGRE_SQL("PostgreSql", "Postgre数据库",
			PostgreSqlDataBaseQuery.class),

	/**
	 * SQL SERVER 2005
	 */
	SQL_SERVER2005("sqlServer2005",
			"SQLServer2005数据库",
			SqlServerDataBaseQuery.class),

	/**
	 * SQLSERVER
	 */
	SQL_SERVER("sqlserver", "SQLServer数据库",
			SqlServerDataBaseQuery.class),

	/**
	 * DM
	 */
	DM("dm", "达梦数据库", DmDataBaseQuery.class),

	/**
	 * HIGHGO
	 */
	HIGHGO("highgo", "瀚高数据库",
			HigHgoDataBaseQuery.class),

	/**
	 * xugu
	 */
	XU_GU("xugu", "虚谷数据库", OtherDataBaseQuery.class),

	/**
	 * Kingbase
	 */
	KINGBASE_ES("kingbasees", "人大金仓数据库",
			OtherDataBaseQuery.class),

	/**
	 * Phoenix
	 */
	PHOENIX("phoenix", "Phoenix HBase数据库",
			PhoenixDataBaseQuery.class),

	/**
	 * CacheDB
	 */
	CACHEDB("cachedb", "Cache 数据库",
			CacheDbDataBaseQuery.class),

	/**
	 * UNKONWN DB
	 */
	OTHER("other", "其他数据库", OtherDataBaseQuery.class);

	/**
	 * 数据库名称
	 */
	@Getter
	private final String name;
	/**
	 * 描述
	 */
	@Getter
	private final String desc;
	/**
	 * 查询实现
	 */
	@Getter
	private final Class<? extends DatabaseQuery> implClass;

	/**
	 * 构造
	 *
	 * @param name  {@link String} 名称
	 * @param desc  {@link String} 描述
	 * @param query {@link Class}
	 */
	DatabaseType(String name, String desc, Class<? extends DatabaseQuery> query) {
		this.name = name;
		this.desc = desc;
		this.implClass = query;
	}

	/**
	 * 获取数据库类型
	 *
	 * @param dbType {@link String} 数据库类型字符串
	 * @return {@link DatabaseType}
	 */
	public static DatabaseType getType(String dbType) {
		DatabaseType[] dts = DatabaseType.values();
		for (DatabaseType dt : dts) {
			if (dt.getName().equalsIgnoreCase(dbType)) {
				return dt;
			}
		}
		return OTHER;
	}

}
