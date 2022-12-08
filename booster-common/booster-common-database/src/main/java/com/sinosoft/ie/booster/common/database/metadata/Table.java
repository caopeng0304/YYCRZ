package com.sinosoft.ie.booster.common.database.metadata;

import java.io.Serializable;

/**
 * 表接口
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/28 15:05
 */
public interface Table extends Serializable {
	/**
	 * 表名
	 *
	 * @return {@link String}
	 */
	String getTableName();

	/**
	 * 获取说明
	 *
	 * @return {@link String}
	 */
	String getRemarks();
}
