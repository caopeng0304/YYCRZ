package com.sinosoft.ie.booster.common.database.metadata;

import java.io.Serializable;

/**
 * 表列接口
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/28 15:05
 */
public interface Column extends Serializable {
	/**
	 * 获取表名
	 *
	 * @return {@link String}
	 */
	String getTableName();

	/**
	 * 表中的列的索引（从 1 开始）
	 *
	 * @return {@link String}
	 */
	String getOrdinalPosition();

	/**
	 * 名称
	 *
	 * @return {@link String}
	 */
	String getColumnName();

	/**
	 * 列的数据类型名称
	 *
	 * @return {@link String}
	 */
	String getTypeName();

	/**
	 * 列表示给定列的指定列大小。
	 * 对于数值数据，这是最大精度。
	 * 对于字符数据，这是字符长度。
	 * 对于日期时间数据类型，这是 String 表示形式的字符长度（假定允许的最大小数秒组件的精度）。
	 * 对于二进制数据，这是字节长度。
	 * 对于 ROWID 数据类型，这是字节长度。对于列大小不适用的数据类型，则返回 Null。
	 *
	 * @return {@link String}
	 */
	String getColumnSize();

	/**
	 * 小数位
	 *
	 * @return {@link String}
	 */
	String getDecimalDigits();

	/**
	 * 可为空
	 *
	 * @return {@link String}
	 */
	String getNullable();

	/**
	 * 是否主键
	 *
	 * @return {@link Boolean}
	 */
	String getPrimaryKey();

	/**
	 * 默认值
	 *
	 * @return {@link String}
	 */
	String getColumnDef();

	/**
	 * 说明
	 *
	 * @return {@link String}
	 */
	String getRemarks();

	/**
	 * 获取列类型
	 *
	 * @return {@link String}
	 */
	String getColumnType();

	/**
	 * 获取列长度
	 *
	 * @return {@link String}
	 */
	String getColumnLength();
}
