package com.sinosoft.ie.booster.common.database.metadata.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:26
 */
@Data
public class DatabaseModel implements Serializable {

	private static final long serialVersionUID = -1975852052060128878L;
	/**
	 * 数据库名称
	 */
	private String database;
	/**
	 * 表
	 */
	private List<TableModel> tables;
}
