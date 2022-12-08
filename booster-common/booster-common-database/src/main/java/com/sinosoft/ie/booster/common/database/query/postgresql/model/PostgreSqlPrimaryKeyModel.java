package com.sinosoft.ie.booster.common.database.query.postgresql.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import lombok.Data;

/**
 * 表主键
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/25 16:52
 */
@Data
public class PostgreSqlPrimaryKeyModel implements PrimaryKey {

	private static final long serialVersionUID = -4908250184995248600L;
	/**
	 * 主键名称
	 */
	@MappingField(value = "pk_name")
	private String pkName;
	/**
	 *
	 */
	@MappingField(value = "table_schem")
	private String tableSchem;
	/**
	 *
	 */
	@MappingField(value = "key_seq")
	private String keySeq;
	/**
	 * tableCat
	 */
	@MappingField(value = "table_cat")
	private String tableCat;
	/**
	 * 列名
	 */
	@MappingField(value = "column_name")
	private String columnName;
	/**
	 * 表名
	 */
	@MappingField(value = "table_name")
	private String tableName;
}
