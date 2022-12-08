package com.sinosoft.ie.booster.common.database.query.oracle.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import lombok.Data;

/**
 * oracle table primary
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/27 11:11
 */
@Data
public class OraclePrimaryKeyModel implements PrimaryKey {

	/**
	 * 表名
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 * pk name
	 */
	@MappingField(value = "PK_NAME")
	private String pkName;
	/**
	 * 表模式
	 */
	@MappingField(value = "TABLE_SCHEM")
	private String tableSchem;
	/**
	 * 列名
	 */
	@MappingField(value = "COLUMN_NAME")
	private String columnName;
	/**
	 * 键序列
	 */
	@MappingField(value = "KEY_SEQ")
	private String keySeq;
}
