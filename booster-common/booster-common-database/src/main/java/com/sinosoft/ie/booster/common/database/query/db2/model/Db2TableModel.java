package com.sinosoft.ie.booster.common.database.query.db2.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import lombok.Data;

/**
 * db2 table
 *
 * @author hexw
 * Created by 964104818@qq.com on 2021/05/31 8:44
 */
@Data
public class Db2TableModel implements Table {
	/**
	 * TABLE_CAT
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * 表名
	 */
	@MappingField(value = "NAME")
	private String tableName;
	/**
	 * 表模式
	 */
	@MappingField(value = "TABLE_SCHEM")
	private String tableSchem;
	/**
	 * 表类型
	 */
	@MappingField(value = "TABLE_TYPE")
	private String tableType;
	/**
	 * 备注
	 */
	@MappingField(value = "REMARKS")
	private String remarks;
}
