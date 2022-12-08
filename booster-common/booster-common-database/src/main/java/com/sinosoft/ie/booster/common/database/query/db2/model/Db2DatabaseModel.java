package com.sinosoft.ie.booster.common.database.query.db2.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Database;
import lombok.Data;

/**
 * db2数据库信息
 *
 * @author hexw
 * Created by 964104818@qq.com on 2021/05/31 8:44
 */
@Data
public class Db2DatabaseModel implements Database {

	private static final long serialVersionUID = 931210775266917894L;
	/**
	 * 数据库名称
	 */
	@MappingField(value = "TABLE_CAT")
	private String database;
}
