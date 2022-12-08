package com.sinosoft.ie.booster.common.database.query.phoenix.model;

import com.sinosoft.ie.booster.common.database.metadata.Database;
import lombok.Data;

/**
 * @author xielongwang
 * Created by siaron.wang@gmail.com 2020/8/19 4:55 下午
 */
@Data
public class PhoenixSqlDatabaseModel implements Database {
	/**
	 * 数据库名称
	 */
	private String database;
}
