package com.sinosoft.ie.booster.common.database.query.mysql.model;

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
public class MySqlPrimaryKeyModel implements PrimaryKey {

	private static final long serialVersionUID = -4908250184995248600L;
	/**
	 * tableCat
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * 表名
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
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
	 * 列名
	 */
	@MappingField(value = "COLUMN_NAME")
	private String columnName;
	/**
	 *
	 */
	@MappingField(value = "KEY_SEQ")
	private String keySeq;
}
