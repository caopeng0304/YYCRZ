package com.sinosoft.ie.booster.common.database.query.phoenix.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import lombok.Data;

/**
 * 表主键
 *
 * @author siaron.wang@gmail.com
 */
@Data
public class PhoenixPrimaryKeyModel implements PrimaryKey {

	private static final long serialVersionUID = -4908250184995248600L;
	/**
	 * 主键名称
	 */
	@MappingField(value = "PK_NAME")
	private String pkName;
	/**
	 *
	 */
	@MappingField(value = "TABLE_SCHEM")
	private String tableSchem;
	/**
	 *
	 */
	@MappingField(value = "KEY_SEQ")
	private String keySeq;
	/**
	 * tableCat
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * 列名
	 */
	@MappingField(value = "COLUMN_NAME")
	private String columnName;
	/**
	 * 表名
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
}
