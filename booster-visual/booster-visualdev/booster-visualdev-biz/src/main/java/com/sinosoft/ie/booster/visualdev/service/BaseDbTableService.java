package com.sinosoft.ie.booster.visualdev.service;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableDataForm;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据管理
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
public interface BaseDbTableService {

	/**
	 * 表列表
	 *
	 * @param dbId 连接Id
	 * @return
	 * @throws DataException
	 */
	List<DbTableModel> getList(Long dbId);

	/**
	 * 表字段
	 *
	 * @param dbId  连接Id
	 * @param table 表名
	 * @return
	 * @throws DataException
	 */
	List<DbTableFieldModel> getFieldList(Long dbId, String table);

	/**
	 * 表数据
	 *
	 * @param dbTableDataForm 分页
	 * @param dbId            连接Id
	 * @param table           表名
	 * @return
	 */
	List<Map<String, Object>> getData(DbTableDataForm dbTableDataForm, Long dbId, String table) throws SQLException, DataException;

	/**
	 * 验证名称
	 *
	 * @param dbId     连接Id
	 * @param table    表名
	 * @param oldTable 主键值
	 * @return
	 */
	boolean isExistByFullName(Long dbId, String table, String oldTable) throws DataException, SQLException;

	/**
	 * 执行sql
	 *
	 * @param dbId   连接Id
	 * @param strSql sql语句
	 * @return
	 * @throws DataException
	 */
	int executeSql(Long dbId, String strSql) throws DataException;

	/**
	 * 删除表
	 *
	 * @param dbId  连接Id
	 * @param table 表名
	 * @throws DataException
	 */
	void delete(Long dbId, String table) throws DataException;

	/**
	 * 创建表
	 *
	 * @param dbId               连接Id
	 * @param dbTableModel       表对象
	 * @param dbTableFieldModels 字段对象
	 * @return
	 * @throws DataException
	 */
	R<Boolean> create(Long dbId, DbTableModel dbTableModel, List<DbTableFieldModel> dbTableFieldModels) throws DataException;

	/**
	 * 修改表
	 *
	 * @param dbId               连接Id
	 * @param dbTableModel       表对象
	 * @param dbTableFieldModels 字段对象
	 * @throws DataException
	 */
	void update(Long dbId, DbTableModel dbTableModel, List<DbTableFieldModel> dbTableFieldModels) throws DataException;
}
