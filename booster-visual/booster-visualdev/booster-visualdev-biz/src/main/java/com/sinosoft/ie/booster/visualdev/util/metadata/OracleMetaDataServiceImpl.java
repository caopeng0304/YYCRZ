package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.database.query.AbstractDatabaseQuery;
import com.sinosoft.ie.booster.visualdev.constant.type.ColumnType;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;
import com.sinosoft.ie.booster.visualdev.util.base.OracleUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Oracle数据库元数据服务
 *
 * @author haocy
 * @since 2022-03-31
 */
public class OracleMetaDataServiceImpl extends AbstractMetaDataService {

	public OracleMetaDataServiceImpl(String driverClassName, String username, String password, String jdbcUrl) {
		super(driverClassName, username, password, jdbcUrl);
	}

	@Override
	public void createTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException {
		StringBuilder sql = new StringBuilder();
		sql.append("create table ").append(dbTableModel.getNewTable()).append(" ");
		sql.append("( ");
		for (DbTableFieldModel item : tableFieldList) {
			switch (item.getDataType()) {
				case ColumnType.MYSQL_VARCHAR:
					sql.append(item.getField()).append(" NVARCHAR2");
					break;
				case ColumnType.MYSQL_DATETIME:
					sql.append(item.getField()).append(" DATE");
					break;
				case ColumnType.MYSQL_DECIMAL:
					sql.append(item.getField()).append(" DECIMAL");
					break;
				case ColumnType.MYSQL_TEXT:
					sql.append(item.getField()).append(" CLOB");
					break;
				default:
					sql.append(item.getField()).append(" NUMBER");
			}
			if (ColumnType.MYSQL_VARCHAR.equals(item.getDataType()) || ColumnType.MYSQL_DECIMAL.equals(item.getDataType())) {
				sql.append("(").append(item.getDataLength()).append(") ");
			}
			if ("1".equals(String.valueOf(item.getPrimaryKey()))) {
				sql.append(" PRIMARY KEY");
			} else if (item.getAllowNull().compareTo(0) == 0) {
				sql.append(" NOT NULL ");
			} else {
				sql.append(" NULL ");
			}
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(");\n");
		//给表添加说明
		sql.append("COMMENT ON TABLE ").append(dbTableModel.getNewTable()).append(" IS '").append(dbTableModel.getTableName()).append("';");
		//给字段添加说明
		for (DbTableFieldModel item : tableFieldList) {
			sql.append("COMMENT ON COLUMN ").append(dbTableModel.getNewTable()).append(".").append(item.getField()).append(" IS '").append(item.getFieldName()).append("';");
		}
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		OracleUtil.oracleCustom(conn, sql.toString());
	}

	@Override
	public void updateTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException {
		StringBuilder sql = new StringBuilder();
		sql.append("drop table ").append(dbTableModel.getTable()).append(";");
		sql.append("create table ").append(dbTableModel.getNewTable()).append(" ");
		sql.append("( ");
		for (DbTableFieldModel item : tableFieldList) {
			switch (item.getDataType()) {
				case ColumnType.MYSQL_VARCHAR:
					sql.append(item.getField()).append(" NVARCHAR2");
					break;
				case ColumnType.MYSQL_DATETIME:
					sql.append(item.getField()).append(" DATE");
					break;
				case ColumnType.MYSQL_DECIMAL:
					sql.append(item.getField()).append(" DECIMAL");
					break;
				case ColumnType.MYSQL_TEXT:
					sql.append(item.getField()).append(" CLOB");
					break;
				default:
					sql.append(item.getField()).append(" NUMBER");
			}
			if (ColumnType.MYSQL_VARCHAR.equals(item.getDataType()) || ColumnType.MYSQL_DECIMAL.equals(item.getDataType())) {
				sql.append("(").append(item.getDataLength()).append(") ");
			}
			if ("1".equals(String.valueOf(item.getPrimaryKey()))) {
				sql.append(" PRIMARY KEY");
			} else if (item.getAllowNull().compareTo(0) == 0) {
				sql.append(" NOT NULL ");
			} else {
				sql.append(" NULL ");
			}
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(");\n");
		//给表添加说明
		sql.append("COMMENT ON TABLE ").append(dbTableModel.getNewTable()).append(" IS '").append(dbTableModel.getTableName()).append("';");
		//给字段添加说明
		for (DbTableFieldModel item : tableFieldList) {
			sql.append("COMMENT ON COLUMN ").append(dbTableModel.getNewTable()).append(".").append(item.getField()).append(" IS '").append(item.getFieldName()).append("';");
		}
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		OracleUtil.oracleCustom(conn, sql.toString());
	}
}
