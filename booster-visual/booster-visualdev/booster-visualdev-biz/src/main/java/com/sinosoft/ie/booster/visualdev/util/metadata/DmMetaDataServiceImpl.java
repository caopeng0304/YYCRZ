package com.sinosoft.ie.booster.visualdev.util.metadata;

import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;

import java.util.List;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.NOT_SUPPORTED;

/**
 * 达梦数据库元数据服务
 *
 * @author haocy
 * @since 2022-03-31
 */
public class DmMetaDataServiceImpl extends AbstractMetaDataService {

	public DmMetaDataServiceImpl(String driverClassName, String username, String password, String jdbcUrl) {
		super(driverClassName, username, password, jdbcUrl);
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
