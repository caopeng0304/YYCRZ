package com.sinosoft.ie.booster.common.database.process;

import com.sinosoft.ie.booster.common.database.Configuration;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import com.sinosoft.ie.booster.common.database.metadata.Database;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import com.sinosoft.ie.booster.common.database.metadata.model.ColumnModel;
import com.sinosoft.ie.booster.common.database.metadata.model.DataModel;
import com.sinosoft.ie.booster.common.database.metadata.model.TableModel;
import com.sinosoft.ie.booster.common.database.query.DatabaseQuery;
import com.sinosoft.ie.booster.common.database.query.DatabaseQueryFactory;
import com.sinosoft.ie.booster.common.database.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.*;

/**
 * 数据模型处理
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/22 21:12
 */
public class DataModelProcess extends AbstractProcess {

	/**
	 * 构造方法
	 *
	 * @param configuration {@link Configuration}
	 */
	public DataModelProcess(Configuration configuration) {
		super(configuration);
	}

	/**
	 * 处理
	 *
	 * @return {@link DataModel}
	 */
	@Override
	public DataModel process() {
		//获取query对象
		DatabaseQuery query = new DatabaseQueryFactory(config.getDataSource()).newInstance();
		DataModel model = new DataModel();
		//Title
		model.setTitle(config.getTitle());
		//org
		model.setOrganization(config.getOrganization());
		//org url
		model.setOrganizationUrl(config.getOrganizationUrl());
		//version
		model.setVersion(config.getVersion());
		//description
		model.setDescription(config.getDescription());

		/*查询操作开始*/
		long start = System.currentTimeMillis();
		//获取数据库
		Database database = query.getDataBase();
		logger.debug("query the database time consuming:{}ms",
				(System.currentTimeMillis() - start));
		model.setDatabase(database.getDatabase());
		start = System.currentTimeMillis();
		//获取全部表
		List<? extends Table> tables = query.getTables();
		logger.debug("query the table time consuming:{}ms", (System.currentTimeMillis() - start));
		//获取全部列
		start = System.currentTimeMillis();
		List<? extends Column> columns = query.getTableColumns();
		logger.debug("query the column time consuming:{}ms", (System.currentTimeMillis() - start));
		//获取主键
		start = System.currentTimeMillis();
		List<? extends PrimaryKey> primaryKeys = query.getPrimaryKeys();
		logger.debug("query the primary key time consuming:{}ms",
				(System.currentTimeMillis() - start));
		/*查询操作结束*/

		/*处理数据开始*/
		start = System.currentTimeMillis();
		List<TableModel> tableModels = new ArrayList<>();
		tablesCaching.put(database.getDatabase(), tables);
		for (Table table : tables) {
			//处理列，表名为key，列名为值
			columnsCaching.put(table.getTableName(),
					columns.stream().filter(i -> i.getTableName().equals(table.getTableName()))
							.collect(Collectors.toList()));
			//处理主键，表名为key，主键为值
			primaryKeysCaching.put(table.getTableName(),
					primaryKeys.stream().filter(i -> i.getTableName().equals(table.getTableName()))
							.collect(Collectors.toList()));
		}
		for (Table table : tables) {
			/*封装数据开始*/
			TableModel tableModel = new TableModel();
			//表名称
			tableModel.setTableName(table.getTableName());
			//说明
			tableModel.setRemarks(table.getRemarks());
			//添加表
			tableModels.add(tableModel);
			//处理列
			List<ColumnModel> columnModels = new ArrayList<>();
			//获取主键
			List<String> key = primaryKeysCaching.get(table.getTableName()).stream()
					.map(PrimaryKey::getColumnName).collect(Collectors.toList());
			for (Column column : columnsCaching.get(table.getTableName())) {
				packageColumn(columnModels, key, column);
			}
			//放入列
			tableModel.setColumns(columnModels);
		}
		//设置表
		model.setTables(filterTables(tableModels));
		//优化数据
		optimizeData(model);
		/*封装数据结束*/
		logger.debug("encapsulation processing data time consuming:{}ms",
				(System.currentTimeMillis() - start));
		return model;
	}

	/**
	 * packageColumn
	 *
	 * @param columnModels {@link List}
	 * @param keyList      {@link List}
	 * @param column       {@link Column}
	 */
	private void packageColumn(List<ColumnModel> columnModels, List<String> keyList,
							   Column column) {
		ColumnModel columnModel = new ColumnModel();
		//表中的列的索引（从 1 开始）
		columnModel.setOrdinalPosition(column.getOrdinalPosition());
		//列名称
		columnModel.setColumnName(column.getColumnName());
		//类型
		columnModel.setColumnType(column.getColumnType());
		//字段名称
		columnModel.setTypeName(column.getTypeName());
		//长度
		columnModel.setColumnLength(column.getColumnLength());
		columnModel.setColumnLength(column.getColumnLength());
		//size
		columnModel.setColumnSize(column.getColumnSize());
		//小数位
		columnModel.setDecimalDigits(
				StringUtils.defaultString(column.getDecimalDigits(), ZERO_DECIMAL_DIGITS));
		//可为空
		columnModel.setNullable(ZERO.equals(column.getNullable()) ? N : Y);
		//是否主键
		columnModel.setPrimaryKey(keyList.contains(column.getColumnName()) ? Y : N);
		//默认值
		columnModel.setColumnDef(column.getColumnDef());
		//说明
		columnModel.setRemarks(column.getRemarks());
		//放入集合
		columnModels.add(columnModel);
	}

}