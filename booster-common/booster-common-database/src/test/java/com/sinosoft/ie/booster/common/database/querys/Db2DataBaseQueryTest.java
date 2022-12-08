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
import java.util.UUID;

/**
 * db2 database test
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/25 16:59
 */
public class Db2DataBaseQueryTest implements Properties {
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
		//config.addDataSourceProperty("remarks", "false");

		config.setPoolName(UUID.randomUUID().toString().replace("-", ""));
		DataSource dataSource = new HikariDataSource(config);
		query = new DatabaseQueryFactory(dataSource).newInstance();
	}

	/**
	 * 查询数据库
	 */
	@Test
	void databases() throws QueryException {
		Database dataBasesInfo = query.getDataBase();
		logger.info(JSON.toJSONString(dataBasesInfo, true));
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
		List<? extends Column> columns = query.getTableColumns();
		logger.info("{}", columns.size());
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
		return System.getProperty("user.dir") + "/src/main/resources/properties/db2.properties";
	}
}
