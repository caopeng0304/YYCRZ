package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableDataForm;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;

import java.util.List;
import java.util.Map;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.NOT_SUPPORTED;

/**
 * 其他数据库 暂未支持系列
 *
 * @author haocy
 * @since 2022-03-31
 */
public class OtherMetaDataServiceImpl extends AbstractMetaDataService {

	public OtherMetaDataServiceImpl(String driverClassName, String username, String password, String jdbcUrl) {
		super(driverClassName, username, password, jdbcUrl);
	}

	@Override
	public List<DbTableModel> getTableList() {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	@Override
	public List<DbTableFieldModel> getFieldList(String table) {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	@Override
	public List<Map<String, Object>> getTableData(String table, DbTableDataForm query) {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	@Override
	public void createTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}

	@Override
	public void updateTableSchema(DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) {
		throw ExceptionUtils.mpe(NOT_SUPPORTED);
	}
}
