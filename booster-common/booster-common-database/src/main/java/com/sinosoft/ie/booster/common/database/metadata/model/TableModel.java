package com.sinosoft.ie.booster.common.database.metadata.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表信息领域对象
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:22
 */
@Data
public class TableModel implements Serializable {

	private static final long serialVersionUID = 825666678767312142L;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 表列
	 */
	private List<ColumnModel> columns;

	/**
	 * 是否弃用
	 */
	private Boolean deprecated;
}
