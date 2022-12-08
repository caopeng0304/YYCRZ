package com.sinosoft.ie.booster.visualdev.constant.type;

import lombok.Data;

/**
 * 常用字段常量类
 *
 * @author booster开发平台组
 * @since 2021-03-15 09:00
 */
@Data
public class ColumnType {

	/**
	 * MySQL字段常量
	 */
	public static final String MYSQL_VARCHAR = "varchar";

	public static final String MYSQL_DATETIME = "datetime";

	public static final String MYSQL_DECIMAL = "decimal";

	public static final String MYSQL_TEXT = "text";

	/**
	 * Oracle字段常量
	 */
	public static final String ORACLE_NVARCHAR = "NVARCHAR2";

	public static final String ORACLE_DATE = "DATE";

	public static final String ORACLE_DECIMAL = "DECIMAL";

	public static final String ORACLE_CLOB = "CLOB";

}
