package com.sinosoft.ie.booster.common.database.query.cachedb.model;

import com.sinosoft.ie.booster.common.database.metadata.Database;
import lombok.Data;

/**
 * 数据库信息
 *
 * @author <a href ='jxh98@foxmail.com'>Josway</a> 2020/6/28
 * @since JDK 1.8
 */
@Data
public class CacheDbDatabaseModel implements Database {

	private static final long serialVersionUID = 931210775266917894L;
	/**
	 * 数据库名称
	 */
	private String database;
}
