package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableDataForm;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据库元数据服务接口
 *
 * @author haocy
 * @since 2022-03-31
 */
public interface DbMetaDataService extends Closeable {

	/**
	 * 获取数据库表
	 *
	 * @return 数据库表列表
	 */
	List<DbTableModel> getTableList();

	/**
	 * 获取数据库表字段
	 *
	 * @param table 表名
	 * @return 数据库表字段列表
	 */
	List<DbTableFieldModel> getFieldList(String table);

	/**
	 * 获取数据库表数据
	 *
	 * @param table 表名
	 * @param query 查询条件
	 * @return 数据库表数据列表
	 */
	List<Map<String, Object>> getTableData(String table, DbTableDataForm query) throws SQLException, DataException;

	/**
	 * 创建数据库表结构
	 *
	 * @param dbTableModel   表模型
	 * @param tableFieldList 字段模型列表
	 */
	void createTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException;

	/**
	 * 更新数据库表结构
	 *
	 * @param dbTableModel   表模型
	 * @param tableFieldList 字段模型列表
	 */
	void updateTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException;
}
