package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.database.query.AbstractDatabaseQuery;
import com.sinosoft.ie.booster.visualdev.constant.type.ColumnType;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * SqlServer数据库元数据服务
 *
 * @author haocy
 * @since 2022-03-31
 */
public class SqlServerMetaDataServiceImpl extends AbstractMetaDataService {

	public SqlServerMetaDataServiceImpl(String driverClassName, String username, String password, String jdbcUrl) {
		super(driverClassName, username, password, jdbcUrl);
	}

	@Override
	public void createTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException {
		StringBuilder sql = new StringBuilder();
		sql.append("create table ").append(dbTableModel.getNewTable()).append(" ");
		sql.append("( ");
		for (DbTableFieldModel item : tableFieldList) {
			sql.append(item.getField()).append(" ").append(item.getDataType());
			if (ColumnType.MYSQL_VARCHAR.equals(item.getDataType()) || ColumnType.MYSQL_DECIMAL.equals(item.getDataType())) {
				sql.append("(").append(item.getDataLength()).append(") ");
			}
			if ("1".equals(String.valueOf(item.getPrimaryKey()))) {
				sql.append(" NOT NULL PRIMARY KEY");
			} else if (item.getAllowNull().compareTo(0) == 0) {
				sql.append(" NOT NULL ");
			} else {
				sql.append(" NULL ");
			}
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(");");
		sql.append("declare @CurrentUser sysname\r\n");
		sql.append("select @CurrentUser = user_name()\r\n");
		sql.append("execute sp_addextendedproperty 'MS_Description', '").append(dbTableModel.getTableName()).append("','user', @CurrentUser, 'table', '").append(dbTableModel.getNewTable()).append("'\r\n");
		for (DbTableFieldModel item : tableFieldList) {
			sql.append("execute sp_addextendedproperty 'MS_Description', '").append(item.getFieldName()).append("', 'user', @CurrentUser, 'table', '").append(dbTableModel.getNewTable()).append("', 'column', '").append(item.getField()).append("'\r\n");
		}
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		JdbcUtil.custom(conn, sql.toString());
	}

	@Override
	public void updateTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException {
		StringBuilder sql = new StringBuilder();
		sql.append("drop table ").append(dbTableModel.getTable()).append(";");
		sql.append("; create table ").append(dbTableModel.getNewTable()).append(" ");
		sql.append("( ");
		for (DbTableFieldModel item : tableFieldList) {
			sql.append(item.getField()).append(" ").append(item.getDataType());
			if (ColumnType.MYSQL_VARCHAR.equals(item.getDataType()) || ColumnType.MYSQL_DECIMAL.equals(item.getDataType())) {
				sql.append("(").append(item.getDataLength()).append(") ");
			}
			if ("1".equals(String.valueOf(item.getPrimaryKey()))) {
				sql.append(" NOT NULL PRIMARY KEY");
			} else if (item.getAllowNull().compareTo(0) == 0) {
				sql.append(" NOT NULL ");
			} else {
				sql.append(" NULL ");
			}
			sql.append(",");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(");");
		sql.append("declare @CurrentUser sysname\r\n");
		sql.append("select @CurrentUser = user_name()\r\n");
		sql.append("execute sp_addextendedproperty 'MS_Description', '").append(dbTableModel.getTableName()).append("','user', @CurrentUser, 'table', '").append(dbTableModel.getNewTable()).append("'\r\n");
		for (DbTableFieldModel item : tableFieldList) {
			sql.append("execute sp_addextendedproperty 'MS_Description', '").append(item.getFieldName()).append("', 'user', @CurrentUser, 'table', '").append(dbTableModel.getNewTable()).append("', 'column', '").append(item.getField()).append("'\r\n");
		}
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		JdbcUtil.custom(conn, sql.toString());
	}
}
