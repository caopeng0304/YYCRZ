package com.sinosoft.ie.booster.common.database.query.postgresql.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import lombok.Data;

/**
 * 表信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:22
 */
@Data
public class PostgreSqlTableModel implements Table {
	/**
	 * refGeneration
	 */
	@MappingField(value = "REF_GENERATION")
	private String refGeneration;
	/**
	 * typeName
	 */
	@MappingField(value = "TYPE_NAME")
	private String typeName;
	/**
	 * typeSchem
	 */
	@MappingField(value = "TYPE_SCHEM")
	private String typeSchem;
	/**
	 * tableSchem
	 */
	@MappingField(value = "TABLE_SCHEM")
	private String tableSchem;
	/**
	 * typeCat
	 */
	@MappingField(value = "TYPE_CAT")
	private String typeCat;
	/**
	 * tableCat
	 */
	@MappingField(value = "TABLE_CAT")
	private Object tableCat;
	/**
	 * 表名称
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 * selfReferencingColName
	 */
	@MappingField(value = "SELF_REFERENCING_COL_NAME")
	private String selfReferencingColName;
	/**
	 * 说明
	 */
	@MappingField(value = "REMARKS")
	private String remarks;
	/**
	 * 表类型
	 */
	@MappingField(value = "TABLE_TYPE")
	private String tableType;
}
