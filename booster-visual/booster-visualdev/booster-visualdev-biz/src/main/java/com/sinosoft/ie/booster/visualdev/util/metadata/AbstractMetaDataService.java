package com.sinosoft.ie.booster.visualdev.util.metadata;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import com.sinosoft.ie.booster.common.database.query.AbstractDatabaseQuery;
import com.sinosoft.ie.booster.common.database.query.DatabaseQuery;
import com.sinosoft.ie.booster.common.database.query.DatabaseQueryFactory;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableDataForm;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库元数据服务抽象类
 *
 * @author haocy
 * @since 2022-03-31
 */
public abstract class AbstractMetaDataService implements DbMetaDataService {

	protected final DatabaseQuery databaseQuery;

	public AbstractMetaDataService(String driverClassName, String username, String password, String jdbcUrl) {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(2);
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		databaseQuery = new DatabaseQueryFactory(new HikariDataSource(config)).newInstance();
	}

	@Override
	public void close() {
		HikariDataSource hikariDataSource = (HikariDataSource) ((AbstractDatabaseQuery) databaseQuery).getDataSource();
		hikariDataSource.close();
	}

	/**
	 * 获取数据库表
	 *
	 * @return 数据库表列表
	 */
	@Override
	public List<DbTableModel> getTableList() {
		List<? extends Table> tables = databaseQuery.getTables();
		List<DbTableModel> dbTableModelList = new ArrayList<>();
		tables.forEach(table -> {
			DbTableModel dbTableModel = new DbTableModel();
			dbTableModel.setTable(table.getTableName());
			dbTableModel.setTableName(table.getRemarks());
			dbTableModel.setDescription(String.format("%s(%s)", table.getTableName(), table.getRemarks()));
			dbTableModelList.add(dbTableModel);
		});
		return dbTableModelList;
	}

	/**
	 * 获取数据库表字段
	 *
	 * @param table 表名
	 * @return 数据库表字段列表
	 */
	@Override
	public List<DbTableFieldModel> getFieldList(String table) {
		List<? extends Column> columns = databaseQuery.getTableColumns(table);
		List<? extends PrimaryKey> primaryKeys = databaseQuery.getPrimaryKeys(table);
		List<DbTableFieldModel> dbTableFieldModelList = new ArrayList<>();
		columns.forEach(column -> {
			DbTableFieldModel dbTableFieldModel = new DbTableFieldModel();
			dbTableFieldModel.setAllowNull(Integer.valueOf(column.getNullable()));
			dbTableFieldModel.setDataLength(column.getColumnSize());
			dbTableFieldModel.setDataType(column.getColumnType());
			dbTableFieldModel.setField(column.getColumnName());
			dbTableFieldModel.setFieldName(column.getRemarks());
			dbTableFieldModel.setPrimaryKey(primaryKeys.stream().anyMatch(primaryKey -> primaryKey.getColumnName().equals(column.getColumnName())) ? 1 : 0);
			dbTableFieldModelList.add(dbTableFieldModel);
		});
		return dbTableFieldModelList;
	}

	/**
	 * 获取数据库表数据
	 *
	 * @param table 表名
	 * @param query 查询条件
	 * @return 数据库表数据列表
	 */
	@Override
	public List<Map<String, Object>> getTableData(String table, DbTableDataForm query) throws SQLException, DataException {
		List<Map<String, Object>> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(table);
		if (!StrUtil.isEmpty(query.getKeyword()) && !StrUtil.isEmpty(query.getField())) {
			sql.append(" where ").append(query.getField()).append(" like '%").append(query.getKeyword()).append("%'");
		}
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		ResultSet result = JdbcUtil.query(conn, sql.toString());
		try {
			ResultSetMetaData md = result.getMetaData();
			int columnCount = md.getColumnCount();
			while (result.next()) {
				Map<String, Object> rowData = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i).toLowerCase(), result.getObject(i));
				}
				list.add(rowData);
			}
		} finally {
			try {
				if (result != null) {
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 创建数据库表结构
	 *
	 * @param dbTableModel   表模型
	 * @param tableFieldList 字段模型列表
	 */
	@Override
	public abstract void createTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException;

	/**
	 * 更新数据库表结构
	 *
	 * @param dbTableModel   表模型
	 * @param tableFieldList 字段模型列表
	 */
	@Override
	public abstract void updateTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException;
}
