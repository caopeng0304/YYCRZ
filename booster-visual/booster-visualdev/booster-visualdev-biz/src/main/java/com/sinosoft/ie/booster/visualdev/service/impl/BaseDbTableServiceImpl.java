package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableDataForm;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableFieldModel;
import com.sinosoft.ie.booster.visualdev.model.dbtable.DbTableModel;
import com.sinosoft.ie.booster.visualdev.service.BaseDbLinkService;
import com.sinosoft.ie.booster.visualdev.service.BaseDbTableService;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.metadata.DbMetaDataService;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;
import com.sinosoft.ie.booster.visualdev.util.PageUtil;
import com.sinosoft.ie.booster.visualdev.util.metadata.DbMetaDataServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据管理
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Slf4j
@Service
public class BaseDbTableServiceImpl implements BaseDbTableService {

	@Autowired
	private BaseDbLinkService dbLinkService;
	@Autowired
	private DataSourceUtil dataSourceUtils;

	@Override
	public List<DbTableModel> getList(Long dbId) {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		List<DbTableModel> list = new ArrayList<>();
		//判断是否为自定义数据源
		if (link != null) {
			DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(link.getDbType());
			if (dataSourceEnum != null) {
				String jdbcUrl = dataSourceEnum.getUrlTemplate()
						.replace("{host}", link.getHost())
						.replace("{port}", String.valueOf(link.getPort()))
						.replace("{dbName}", link.getServiceName());
				DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(dataSourceEnum.getDriverClassName(), link.getUserName(), link.getPassword(), jdbcUrl);
				try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
					list = dbMetaDataService.getTableList();
				} catch (IOException e) {
					log.error(e.getMessage());
				}

			}
		} else {
			DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(
					dataSourceUtils.getDriverClassName(), dataSourceUtils.getUserName(), dataSourceUtils.getPassword(),
					dataSourceUtils.getUrl());
			try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
				list = dbMetaDataService.getTableList();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return list;
	}

	@Override
	public List<DbTableFieldModel> getFieldList(Long dbId, String table) {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		List<DbTableFieldModel> list = new ArrayList<>();
		//判断是否为自定义数据源
		if (link != null) {
			DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(link.getDbType());
			if (dataSourceEnum != null) {
				String jdbcUrl = dataSourceEnum.getUrlTemplate()
						.replace("{host}", link.getHost())
						.replace("{port}", String.valueOf(link.getPort()))
						.replace("{dbName}", link.getServiceName());
				DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(dataSourceEnum.getDriverClassName(), link.getUserName(), link.getPassword(), jdbcUrl);
				try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
					list = dbMetaDataService.getFieldList(table);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(
					dataSourceUtils.getDriverClassName(), dataSourceUtils.getUserName(), dataSourceUtils.getPassword(),
					dataSourceUtils.getUrl());
			try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
				list = dbMetaDataService.getFieldList(table);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getData(DbTableDataForm dbTableDataForm, Long dbId, String table) throws SQLException, DataException {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		List<Map<String, Object>> list = new ArrayList<>();
		if (link != null) {
			DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(link.getDbType());
			if (dataSourceEnum != null) {
				String jdbcUrl = dataSourceEnum.getUrlTemplate()
						.replace("{host}", link.getHost())
						.replace("{port}", String.valueOf(link.getPort()))
						.replace("{dbName}", link.getServiceName());
				DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(dataSourceEnum.getDriverClassName(), link.getUserName(), link.getPassword(), jdbcUrl);
				try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
					list = dbMetaDataService.getTableData(table, dbTableDataForm);
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(
					dataSourceUtils.getDriverClassName(), dataSourceUtils.getUserName(), dataSourceUtils.getPassword(),
					dataSourceUtils.getUrl());
			try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
				list = dbMetaDataService.getTableData(table, dbTableDataForm);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return dbTableDataForm.setData(PageUtil.getListPage((int) dbTableDataForm.getCurrentPage(), (int) dbTableDataForm.getPageSize(), list), list.size());
	}

	@Override
	public boolean isExistByFullName(Long dbId, String table, String oldTable) throws DataException, SQLException {
		List<DbTableModel> data = this.getList(dbId).stream().filter(m -> m.getTable().equals(table)).collect(Collectors.toList());
		if (!StrUtil.isEmpty(oldTable)) {
			data = data.stream().filter(m -> !m.getTable().equals(oldTable)).collect(Collectors.toList());
		}
		return data.size() > 0;
	}

	@Override
	public int executeSql(Long dbId, String strSql) throws DataException {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		int result = 0;
		if (link != null) {
			Connection conn = JdbcUtil.getConn(link.getDbType().toLowerCase(), link.getUserName(), link.getPassword(), link.getHost(), link.getPort(), link.getServiceName());
			if (conn != null) {
				result = JdbcUtil.custom(conn, strSql);
			}
		}
		return result;
	}

	@Override
	public void delete(Long dbId, String table) throws DataException {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		if (link != null) {
			Connection conn = JdbcUtil.getConn(link.getDbType().toLowerCase(), link.getUserName(), link.getPassword(), link.getHost(), link.getPort(), link.getServiceName());
			if (conn != null) {
				JdbcUtil.custom(conn, "drop table " + table);
			}
		} else {
			String url = dataSourceUtils.getUrl();
			String driverClassName = dataSourceUtils.getDriverClassName();
			Connection conn = JdbcUtil.getConn(dataSourceUtils.getUserName(), dataSourceUtils.getPassword(), url, driverClassName);
			if (conn != null) {
				JdbcUtil.custom(conn, "drop table " + table);
			}
		}
	}

	@Override
	public R<Boolean> create(Long dbId, DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws DataException {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		if (link != null) {
			DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(link.getDbType());
			if (dataSourceEnum != null) {
				String jdbcUrl = dataSourceEnum.getUrlTemplate()
						.replace("{host}", link.getHost())
						.replace("{port}", String.valueOf(link.getPort()))
						.replace("{dbName}", link.getServiceName());
				DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(dataSourceEnum.getDriverClassName(), link.getUserName(), link.getPassword(), jdbcUrl);
				try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
					dbMetaDataService.createTableSchema(dbTableModel, tableFieldList);
				} catch (IOException | SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(
					dataSourceUtils.getDriverClassName(), dataSourceUtils.getUserName(), dataSourceUtils.getPassword(),
					dataSourceUtils.getUrl());
			try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
				dbMetaDataService.createTableSchema(dbTableModel, tableFieldList);
			} catch (IOException | SQLException e) {
				log.error(e.getMessage());
			}
		}
		return R.ok(null, "新建成功");
	}

	@Override
	public void update(Long dbId, DbTableModel dbTableModel, List<DbTableFieldModel> tableFieldList) throws DataException {
		BaseDbLinkEntity link = dbLinkService.getInfo(dbId);
		if (link != null) {
			DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(link.getDbType());
			if (dataSourceEnum != null) {
				String jdbcUrl = dataSourceEnum.getUrlTemplate()
						.replace("{host}", link.getHost())
						.replace("{port}", String.valueOf(link.getPort()))
						.replace("{dbName}", link.getServiceName());
				DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(dataSourceEnum.getDriverClassName(), link.getUserName(), link.getPassword(), jdbcUrl);
				try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
					dbMetaDataService.updateTableSchema(dbTableModel, tableFieldList);
				} catch (IOException | SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			DbMetaDataServiceFactory dbMetaDataServiceFactory = new DbMetaDataServiceFactory(
					dataSourceUtils.getDriverClassName(), dataSourceUtils.getUserName(), dataSourceUtils.getPassword(),
					dataSourceUtils.getUrl());
			try (DbMetaDataService dbMetaDataService = dbMetaDataServiceFactory.newInstance()) {
				dbMetaDataService.updateTableSchema(dbTableModel, tableFieldList);
			} catch (IOException | SQLException e) {
				log.error(e.getMessage());
			}
		}
	}

}
