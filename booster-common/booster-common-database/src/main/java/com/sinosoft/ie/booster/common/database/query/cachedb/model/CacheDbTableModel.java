package com.sinosoft.ie.booster.common.database.query.cachedb.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import lombok.Data;

/**
 * 表信息
 *
 * @author <a href ='jxh98@foxmail.com'>Josway</a> 2020/6/28
 * @since JDK 1.8
 */
@Data
public class CacheDbTableModel implements Table {

	private static final long serialVersionUID = -1917761911812533673L;
	/**
	 *
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * TABLE_NAME
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 *
	 */
	@MappingField(value = "SELF_REFERENCING_COL_NAME")
	private String selfReferencingColName;
	/**
	 * TABLE_SCHEM
	 */
	@MappingField(value = "TABLE_SCHEM")
	private String tableSchem;
	/**
	 *
	 */
	@MappingField(value = "TYPE_SCHEM")
	private String typeSchem;
	/**
	 *
	 */
	@MappingField(value = "TYPE_CAT")
	private Object typeCat;
	/**
	 * TABLE_TYPE
	 */
	@MappingField(value = "TABLE_TYPE")
	private String tableType;
	/**
	 * REMARKS
	 */
	@MappingField(value = "REMARKS")
	private String remarks;
	/**
	 *
	 */
	@MappingField(value = "REF_GENERATION")
	private String refGeneration;
	/**
	 *
	 */
	@MappingField(value = "TYPE_NAME")
	private String typeName;
}
