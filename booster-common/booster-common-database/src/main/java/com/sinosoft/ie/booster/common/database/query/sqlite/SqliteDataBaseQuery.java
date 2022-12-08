package com.sinosoft.ie.booster.common.database.query.sqlite;

import com.sinosoft.ie.booster.common.database.exception.QueryException;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import com.sinosoft.ie.booster.common.database.metadata.Database;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import com.sinosoft.ie.booster.common.database.query.AbstractDatabaseQuery;
import com.sinosoft.ie.booster.common.database.util.Assert;
import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;

import javax.sql.DataSource;
import java.util.List;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.NOT_SUPPORTED;

/**
 * Sqlite 查询
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/18 13:58
 */
public class SqliteDataBaseQuery extends AbstractDatabaseQuery {
	/**
	 * 构造函数
	 *
	 * @param dataSource {@link DataSource}
	 */
	public SqliteDataBaseQuery(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 获取数据库
	 *
	 * @return {@link Database} 数据库信息
	 */
	@Override
	public Database getDataBase() throws QueryException {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	/**
	 * 获取表信息
	 *
	 * @return {@link List} 所有表信息
	 */
	@Override
	public List<Table> getTables() {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	/**
	 * 获取列信息
	 *
	 * @param table {@link String} 表名
	 * @return {@link List} 表字段信息
	 * @throws QueryException QueryException
	 */
	@Override
	public List<Column> getTableColumns(String table) throws QueryException {
		Assert.notEmpty(table, "Table name can not be empty!");
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	/**
	 * 获取所有列信息
	 *
	 * @return {@link List} 表字段信息
	 * @throws QueryException QueryException
	 */
	@Override
	public List<? extends Column> getTableColumns() throws QueryException {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	/**
	 * 根据表名获取主键
	 *
	 * @param table {@link String}
	 * @return {@link List}
	 * @throws QueryException QueryException
	 */
	@Override
	public List<? extends PrimaryKey> getPrimaryKeys(String table) throws QueryException {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

}
