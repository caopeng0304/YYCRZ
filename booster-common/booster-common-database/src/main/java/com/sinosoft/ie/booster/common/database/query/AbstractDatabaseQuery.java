package com.sinosoft.ie.booster.common.database.query;

import com.sinosoft.ie.booster.common.database.exception.QueryException;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import com.sinosoft.ie.booster.common.database.util.Assert;
import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;
import com.sinosoft.ie.booster.common.database.util.JdbcUtils;
import com.sinosoft.ie.booster.common.database.util.StringUtils;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.NOT_SUPPORTED;

/**
 * AbstractDataBaseQuery
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/18 12:50
 */
public abstract class AbstractDatabaseQuery implements DatabaseQuery {
	/**
	 * 缓存
	 */
	protected final Map<String, List<Column>> columnsCaching = new ConcurrentHashMap<>();
	/**
	 * DataSource
	 */
	@Getter
	private final DataSource dataSource;

	/**
	 * Connection 双重锁，线程安全
	 */
	volatile protected Connection connection;

	public AbstractDatabaseQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取连接对象，单例模式，采用双重锁检查
	 *
	 * @return {@link Connection}
	 * @throws QueryException QueryException
	 */
	private Connection getConnection() throws QueryException {
		try {
			//不为空
			if (!Objects.isNull(connection) && !connection.isClosed()) {
				System.out.println(connection);
				return connection;
			}
			//同步代码块
			synchronized (AbstractDatabaseQuery.class) {
				//为空或者已经关闭
				if (Objects.isNull(connection) || connection.isClosed()) {
					this.connection = this.getDataSource().getConnection();
				}
			}
			return this.connection;
		} catch (SQLException e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 获取 getCatalog
	 *
	 * @return {@link String}
	 * @throws QueryException QueryException
	 */
	protected String getCatalog() throws QueryException {
		try {
			String catalog = this.getConnection().getCatalog();
			if (StringUtils.isBlank(catalog)) {
				return null;
			}
			return catalog;
		} catch (SQLException e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 获取 getSchema
	 *
	 * @return {@link String}
	 * @throws QueryException QueryException
	 */
	protected String getSchema() throws QueryException {
		try {
			String schema;
			//获取数据库URL 用于判断数据库类型
			String url = this.getConnection().getMetaData().getURL();
			//获取数据库名称
			String name = JdbcUtils.getDbType(url).getName();
			if (DatabaseType.CACHEDB.getName().equals(name)) {
				schema = verifySchema(this.getDataSource());
			} else {
				schema = this.getConnection().getSchema();
			}

			if (StringUtils.isBlank(schema)) {
				return null;
			}
			return schema;
		} catch (SQLException e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 验证Schema
	 *
	 * @param dataSource {@link DataSource}
	 * @return Schema
	 */
	private String verifySchema(DataSource dataSource) throws SQLException {
		String schema;
		if (dataSource instanceof HikariDataSource) {
			schema = ((HikariDataSource) dataSource).getSchema();
		} else {
			schema = dataSource.getConnection().getSchema();
		}

		//验证是否有此Schema
		ResultSet resultSet = this.getConnection().getMetaData().getSchemas();
		while (resultSet.next()) {
			int columnCount = resultSet.getMetaData().getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnValue = resultSet.getString(i);
				if (StringUtils.isNotBlank(columnValue) && columnValue.contains(schema)) {
					return schema;
				}
			}
		}
		return null;
	}

	/**
	 * 获取 DatabaseMetaData
	 *
	 * @return {@link DatabaseMetaData}
	 * @throws QueryException QueryException
	 */
	protected DatabaseMetaData getMetaData() throws QueryException {
		try {
			return this.getConnection().getMetaData();
		} catch (SQLException e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 准备声明
	 *
	 * @param sql {@link String} SQL
	 * @return {@link PreparedStatement}
	 * @throws QueryException QueryException
	 */
	protected PreparedStatement prepareStatement(String sql) throws QueryException {
		Assert.notEmpty(sql, "Sql can not be empty!");
		try {
			return this.getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 根据表名获取主键
	 *
	 * @return {@link List}
	 * @throws QueryException QueryException
	 */
	@Override
	public List<? extends PrimaryKey> getPrimaryKeys() throws QueryException {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}
}
