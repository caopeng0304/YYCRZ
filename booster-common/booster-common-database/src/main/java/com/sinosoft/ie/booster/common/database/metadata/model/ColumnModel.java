package com.sinosoft.ie.booster.common.database.metadata.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 表列领域对象，目前包含如下内容
 * <p>
 * 名称
 * <p>
 * 数据类型
 * <p>
 * 长度
 * <p>
 * 小数位
 * <p>
 * 允许空值
 * <p>
 * 主键
 * <p>
 * 默认值
 * <p>
 * 说明
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:24
 */
@Data
public class ColumnModel implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5417752216907437665L;
	/**
	 * 表中的列的索引（从 1 开始）
	 */
	private String ordinalPosition;
	/**
	 * 名称
	 */
	private String columnName;
	/**
	 * SQL 数据类型带长度
	 */
	private String columnType;
	/**
	 * SQL 数据类型 名称
	 */
	private String typeName;
	/**
	 * 列长度
	 */
	private String columnLength;
	/**
	 * 列大小
	 */
	private String columnSize;
	/**
	 * 小数位
	 */
	private String decimalDigits;
	/**
	 * 可为空
	 */
	private String nullable;
	/**
	 * 是否主键
	 */
	private String primaryKey;
	/**
	 * 默认值
	 */
	private String columnDef;
	/**
	 * 说明
	 */
	private String remarks;

	/**
	 * 嵌套数据信息（用于文档数据库）
	 */
	private TableModel nestedTable;

	/**
	 * 是否弃用
	 */
	private Boolean deprecated;
}
