package com.sinosoft.ie.booster.common.database.query.cachedb.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import lombok.Data;

/**
 * 表主键
 *
 * @author <a href ='jxh98@foxmail.com'>Josway</a> 2020/6/28
 * @since JDK 1.8
 */
@Data
public class CacheDbPrimaryKeyModel implements PrimaryKey {

	private static final long serialVersionUID = -4908250184995248600L;
	/**
	 *
	 */
	@MappingField(value = "TABLE_CATALOG")
	private String tableCat;
	/**
	 * TABLE_NAME
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 * TABLE_SCHEM
	 */
	@MappingField(value = "TABLE_SCHEM")
	private String tableSchem;
	/**
	 * COLUMN_NAME
	 */
	@MappingField(value = "COLUMN_NAME")
	private String columnName;
	/**
	 * KEY_SEQ
	 */
	@MappingField(value = "KEY_SEQ")
	private String keySeq;
	private String pkName;
}
