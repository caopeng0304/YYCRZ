package com.sinosoft.ie.booster.common.database.metadata;

import java.io.Serializable;

/**
 * 数据库接口
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/28 15:05
 */
public interface Database extends Serializable {
	/**
	 * 获取数据库名称
	 *
	 * @return {@link String}
	 */
	String getDatabase();
}
