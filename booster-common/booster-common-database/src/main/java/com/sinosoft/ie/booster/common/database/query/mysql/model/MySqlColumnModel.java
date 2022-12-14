package com.sinosoft.ie.booster.common.database.query.mysql.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Column;
import lombok.Data;

/**
 * 表字段信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:24
 */
@Data
public class MySqlColumnModel implements Column {

	private static final long serialVersionUID = -7231934486902707912L;
	/**
	 *
	 */
	@MappingField(value = "SCOPE_TABLE")
	private Object scopeTable;
	/**
	 *
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 *
	 */
	@MappingField(value = "BUFFER_LENGTH")
	private String bufferLength;
	/**
	 *
	 */
	@MappingField(value = "IS_NULLABLE")
	private String isNullable;
	/**
	 * 表名
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 * 默认值
	 */
	@MappingField(value = "COLUMN_DEF")
	private String columnDef;
	/**
	 *
	 */
	@MappingField(value = "SCOPE_CATALOG")
	private Object scopeCatalog;
	/**
	 *
	 */
	@MappingField(value = "TABLE_SCHEM")
	private Object tableSchem;
	/**
	 *
	 */
	@MappingField(value = "COLUMN_NAME")
	private String columnName;
	/**
	 *
	 */
	@MappingField(value = "NULLABLE")
	private String nullable;
	/**
	 * 说明
	 */
	@MappingField(value = "REMARKS")
	private String remarks;
	/**
	 * 小数位
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
	 *
	 */
	@MappingField(value = "IS_GENERATEDCOLUMN")
	private String isGeneratedColumn;
	/**
	 *
	 */
	@MappingField(value = "IS_AUTOINCREMENT")
	private String isAutoIncrement;
	/**
	 *
	 */
	@MappingField(value = "SQL_DATA_TYPE")
	private String sqlDataType;
	/**
	 *
	 */
	@MappingField(value = "CHAR_OCTET_LENGTH")
	private String charOctetLength;
	/**
	 *
	 */
	@MappingField(value = "ORDINAL_POSITION")
	private String ordinalPosition;
	/**
	 *
	 */
	@MappingField(value = "SCOPE_SCHEMA")
	private Object scopeSchema;
	/**
	 *
	 */
	@MappingField(value = "SOURCE_DATA_TYPE")
	private Object sourceDataType;
	/**
	 * 数据类型
	 */
	@MappingField(value = "DATA_TYPE")
	private String dataType;
	/**
	 *
	 */
	@MappingField(value = "TYPE_NAME")
	private String typeName;
	/**
	 * 列表示给定列的指定列大小。
	 * 对于数值数据，这是最大精度。
	 * 对于字符数据，这是字符长度。
	 * 对于日期时间数据类型，这是 String 表示形式的字符长度（假定允许的最大小数秒组件的精度）。
	 * 对于二进制数据，这是字节长度。
	 * 对于 ROWID 数据类型，这是字节长度。对于列大小不适用的数据类型，则返回 Null。
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
