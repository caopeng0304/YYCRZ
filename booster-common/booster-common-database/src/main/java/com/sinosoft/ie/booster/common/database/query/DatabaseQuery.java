package com.sinosoft.ie.booster.common.database.query;

import com.sinosoft.ie.booster.common.database.exception.QueryException;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import com.sinosoft.ie.booster.common.database.metadata.Database;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import com.sinosoft.ie.booster.common.database.metadata.Table;

import java.io.Serializable;
import java.util.List;

/**
 * 通用查询接口
 * 查询数据库信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/18 11:58
 */
public interface DatabaseQuery extends Serializable {
	/**
	 * 获取数据库
	 *
	 * @return {@link Database} 数据库信息
	 * @throws QueryException QueryException
	 */
	Database getDataBase() throws QueryException;

	/**
	 * 获取表信息
	 *
	 * @return {@link List} 所有表信息
	 * @throws QueryException QueryException
	 */
	List<? extends Table> getTables() throws QueryException;

	/**
	 * 获取列信息
	 *
	 * @param table {@link String} 表名
	 * @return {@link List} 表字段信息
	 * @throws QueryException QueryException
	 */
	List<? extends Column> getTableColumns(String table) throws QueryException;

	/**
	 * 获取所有列信息
	 *
	 * @return {@link List} 表字段信息
	 * @throws QueryException QueryException
	 */
	List<? extends Column> getTableColumns() throws QueryException;

	/**
	 * 根据表名获取主键
	 *
	 * @param table {@link String}
	 * @return {@link List}
	 * @throws QueryException QueryException
	 */
	List<? extends PrimaryKey> getPrimaryKeys(String table) throws QueryException;

	/**
	 * 获取主键
	 *
	 * @return {@link List}
	 * @throws QueryException QueryException
	 */
	List<? extends PrimaryKey> getPrimaryKeys() throws QueryException;
}
