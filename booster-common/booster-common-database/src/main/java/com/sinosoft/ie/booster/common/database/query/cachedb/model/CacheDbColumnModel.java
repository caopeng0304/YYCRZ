package com.sinosoft.ie.booster.common.database.query.cachedb.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import lombok.Data;

/**
 * 表字段信息
 *
 * @author <a href ='jxh98@foxmail.com'>Josway</a> 2020/6/28
 * @since JDK 1.8
 */
@Data
public class CacheDbColumnModel implements Column {

	private static final long serialVersionUID = -7231934486902707912L;

	/**
	 *
	 */
	@MappingField(value = "SCOPE_TABLE")
	private String scopeTable;
	/**
	 *
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * BUFFER_LENGTH
	 */
	@MappingField(value = "BUFFER_LENGTH")
	private String bufferLength;
	/**
	 * IS_NULLABLE
	 */
	@MappingField(value = "IS_NULLABLE")
	private String isNullable;
	/**
	 * TABLE_NAME
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 *
	 */
	@MappingField(value = "COLUMN_DEF")
	private String columnDef;
	/**
	 *
	 */
	@MappingField(value = "SCOPE_CATALOG")
	private String scopeCatalog;
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
	 * nullable
	 */
	@MappingField(value = "NULLABLE")
	private String nullable;
	/**
	 * REMARKS
	 */
	@MappingField(value = "REMARKS")
	private String remarks;
	/**
	 * DECIMAL_DIGITS
	 */
	@MappingField(value = "DECIMAL_DIGITS")
	private String decimalDigits;
	/**
	 *
	 */
	@MappingField(value = "NUM_PREC_RADIX")
	private String numPrecRadix;
	/**
	 *
	 */
	@MappingField(value = "SQL_DATETIME_SUB")
	private String sqlDatetimeSub;
	/**
	 * IS_GENERATEDCOLUMN
	 */
	@MappingField(value = "IS_GENERATEDCOLUMN")
	private String isGeneratedcolumn;
	/**
	 * IS_AUTOINCREMENT
	 */
	@MappingField(value = "IS_AUTOINCREMENT")
	private String isAutoincrement;
	/**
	 * SQL_DATA_TYPE
	 */
	@MappingField(value = "SQL_DATA_TYPE")
	private String sqlDataType;
	/**
	 * CHAR_OCTET_LENGTH
	 */
	@MappingField(value = "CHAR_OCTET_LENGTH")
	private String charOctetLength;
	/**
	 * ORDINAL_POSITION
	 */
	@MappingField(value = "ORDINAL_POSITION")
	private String ordinalPosition;
	/**
	 *
	 */
	@MappingField(value = "SCOPE_SCHEMA")
	private String scopeSchema;
	/**
	 *
	 */
	@MappingField(value = "SOURCE_DATA_TYPE")
	private String sourceDataType;
	/**
	 * DATA_TYPE
	 */
	@MappingField(value = "DATA_TYPE")
	private String dataType;
	/**
	 * TYPE_NAME
	 */
	@MappingField(value = "TYPE_NAME")
	private String typeName;
	/**
	 * COLUMN_SIZE
	 */
	@MappingField(value = "COLUMN_SIZE")
	private String columnSize;
	/**
	 * 是否主键
	 */
	private String primaryKey;
	/**
	 * 列类型（带长度）
	 */
	@MappingField(value = "COLUMN_TYPE")
	private String columnType;

	/**
	 * 列长度
	 */
	@MappingField(value = "COLUMN_LENGTH")
	private String columnLength;
}
