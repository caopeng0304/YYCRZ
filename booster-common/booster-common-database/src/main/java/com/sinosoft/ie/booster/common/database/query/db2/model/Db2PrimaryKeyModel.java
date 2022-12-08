package com.sinosoft.ie.booster.common.database.query.db2.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.PrimaryKey;
import lombok.Data;

/**
 * db2 table primary
 *
 * @author hexw
 * Created by 964104818@qq.com on 2021/05/31 8:44
 */
@Data
public class Db2PrimaryKeyModel implements PrimaryKey {

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
