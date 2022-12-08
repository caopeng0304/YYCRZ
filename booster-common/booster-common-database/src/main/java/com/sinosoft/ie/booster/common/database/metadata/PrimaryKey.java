package com.sinosoft.ie.booster.common.database.metadata;

import java.io.Serializable;

/**
 * 主键
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/28 21:37
 */
public interface PrimaryKey extends Serializable {
	/**
	 * 表名
	 *
	 * @return {@link String}
	 */
	String getTableName();

	/**
	 * 主键名称
	 *
	 * @return {@link String}
	 */
	String getPkName();

	/**
	 * 列名
	 *
	 * @return {@link String}
	 */
	String getColumnName();

	/**
	 * 关键序列
	 *
	 * @return {@link String}
	 */
	String getKeySeq();
}
