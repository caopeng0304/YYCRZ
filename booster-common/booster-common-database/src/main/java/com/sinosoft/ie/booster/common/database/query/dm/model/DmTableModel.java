package com.sinosoft.ie.booster.common.database.query.dm.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import lombok.Data;

/**
 * oracle table
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/27 8:44
 */
@Data
public class DmTableModel implements Table {
	/**
	 * TABLE_CAT
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * 表名
	 */
	@MappingField(value = "TABLE_NAME")
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
