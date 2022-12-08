package com.sinosoft.ie.booster.common.database.querys;

import com.alibaba.fastjson.JSON;
import com.sinosoft.ie.booster.common.database.common.Properties;
import com.sinosoft.ie.booster.common.database.exception.QueryException;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import com.sinosoft.ie.booster.common.database.metadata.Database;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import com.sinosoft.ie.booster.common.database.query.DatabaseQuery;
import com.sinosoft.ie.booster.common.database.query.DatabaseQueryFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * PostgreSQL
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/29 22:54
 */
public class PostgreSQLDataBaseQueryTest implements Properties {
	/**
	 * 日志
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * DataBaseQuery
	 */
	private DatabaseQuery query;

	/**
	 * before
	 */
	@BeforeEach
	void before() throws IOException {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(getDriver());
		config.setJdbcUrl(getUrl());
		config.setUsername(getUserName());
		config.setPassword(getPassword());
		DataSource dataSource = new HikariDataSource(config);
		query =

				new DatabaseQueryFactory(dataSource).newInstance();
	}

	/**
	 * 查询数据库
	 */
	@Test
	void databases() throws QueryException {
		Database database = query.getDataBase();
		logger.info(JSON.toJSONString(database, true));
	}

	/**
	 * 查询表
	 */
	@Test
	void tables() throws QueryException {
		List<? extends Table> tables = query.getTables();
		logger.info(JSON.toJSONString(tables, true));
	}

	/**
	 * 查询列
	 */
	@Test
	void column() throws QueryException {
		List<? extends Column> columns = query.getTableColumns("sys_user_info");
		logger.info(JSON.toJSONString(columns, true));
	}

	/**
	 * 查询主键
	 */
	@Test
	void primaryKeys() {
		List<? extends PrimaryKey> primaryKeys = query.getPrimaryKeys();
		logger.info(JSON.toJSONString(primaryKeys, true));
	}

	/**
	 * 获取配置文件
	 *
	 * @return {@link java.util.Properties}
	 */
	@Override
	public String getConfigProperties() {
		return System.getProperty("user.dir")
				+ "/src/main/resources/properties/postgresql.properties";
	}
}
