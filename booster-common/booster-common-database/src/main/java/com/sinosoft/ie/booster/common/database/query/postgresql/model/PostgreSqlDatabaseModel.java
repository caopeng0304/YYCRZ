package com.sinosoft.ie.booster.common.database.query.postgresql.model;

import com.sinosoft.ie.booster.common.database.metadata.Database;
import lombok.Data;

/**
 * 数据库信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:26
 */
@Data
public class PostgreSqlDatabaseModel implements Database {

	private static final long serialVersionUID = 931210775266917894L;
	/**
	 * 数据库名称
	 */
	private String database;
}
