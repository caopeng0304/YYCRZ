package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.database.query.AbstractDatabaseQuery;
import com.sinosoft.ie.booster.visualdev.constant.type.ColumnType;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * MySql数据库元数据服务
 *
 * @author haocy
 * @since 2022-03-31
 */
public class MySqlMetaDataServiceImpl extends AbstractMetaDataService {

	public MySqlMetaDataServiceImpl(String driverClassName, String username, String password, String jdbcUrl) {
		super(driverClassName, username, password, jdbcUrl);
	}

	@Override
	public void createTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException {
		StringBuilder sql = new StringBuilder();
		sql.append("create table ").append(dbTableModel.getNewTable()).append(" ");
		sql.append("( ");
		for (DbTableFieldModel item : tableFieldList) {
			sql.append("`").append(item.getField()).append("` ").append(item.getDataType());
			if (ColumnType.MYSQL_VARCHAR.equals(item.getDataType()) || ColumnType.MYSQL_DECIMAL.equals(item.getDataType())) {
				sql.append("(").append(item.getDataLength()).append(") ");
			}

			if ("1".equals(String.valueOf(item.getPrimaryKey()))) {
				sql.append(" NOT NULL PRIMARY KEY COMMENT '").append(item.getFieldName()).append("',");
			} else if (item.getAllowNull().compareTo(0) == 0) {
				sql.append(" NOT NULL COMMENT '").append(item.getFieldName()).append("',");
			} else {
				sql.append(" NULL COMMENT '").append(item.getFieldName()).append("',");
			}
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") COMMENT = '").append(dbTableModel.getTableName()).append("'");
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		JdbcUtil.custom(conn, sql.toString());
	}

	@Override
	public void updateTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws SQLException, DataException {
		String delSql;
		StringBuilder crSql = new StringBuilder();
		//先随机生成一个表名
		String ramTable = dbTableModel.getNewTable() + "_" + RandomUtil.enUuId();
		delSql = "drop table IF EXISTS " + dbTableModel.getTable();
		crSql.append("create table ").append(ramTable).append(" ");
		crSql.append("( ");
		for (DbTableFieldModel item : tableFieldList) {
			crSql.append("`").append(item.getField()).append("` ").append(item.getDataType());
			if (ColumnType.MYSQL_VARCHAR.equals(item.getDataType()) || ColumnType.MYSQL_DECIMAL.equals(item.getDataType())) {
				crSql.append("(").append(item.getDataLength()).append(") ");
			}
			if ("1".equals(String.valueOf(item.getPrimaryKey()))) {
				crSql.append(" NOT NULL PRIMARY KEY COMMENT '").append(item.getFieldName()).append("',");
			} else if (item.getAllowNull().compareTo(0) == 0) {
				crSql.append(" NOT NULL COMMENT '").append(item.getFieldName()).append("',");
			} else {
				crSql.append(" NULL COMMENT '").append(item.getFieldName()).append("',");
			}
		}
		crSql = crSql.deleteCharAt(crSql.length() - 1);
		crSql.append(") COMMENT = '").append(dbTableModel.getTableName()).append("'");
		Connection conn = ((AbstractDatabaseQuery) databaseQuery).getDataSource().getConnection();
		JdbcUtil.upTableFields(conn, delSql, crSql.toString(), ramTable, dbTableModel.getNewTable());
	}
}
