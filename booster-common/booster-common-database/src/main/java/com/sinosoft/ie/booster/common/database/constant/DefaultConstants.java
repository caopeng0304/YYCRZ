package com.sinosoft.ie.booster.common.database.constant;

import java.io.Serializable;

/**
 * 默认常量
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/20 18:33
 */
public class DefaultConstants implements Serializable {
	/**
	 * 名称
	 */
	public static final String NAME = "screw";
	/**
	 * 百分号
	 */
	public static final String PERCENT_SIGN = "%";
	/**
	 * 暂未支持
	 */
	public static final String NOT_SUPPORTED = "Not supported yet!";

	/**
	 * 默认字符集
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 默认国际化
	 */
	public static final String DEFAULT_LOCALE = "zh_CN";
	/**
	 * Mac
	 */
	public static final String MAC = "Mac";
	/**
	 * Windows
	 */
	public static final String WINDOWS = "Windows";
	/**
	 * 小数点0
	 */
	public static final String ZERO_DECIMAL_DIGITS = "0";
	/**
	 * 默认描述
	 */
	public static final String DESCRIPTION = "数据库设计文档";
	/**
	 * mysql useInformationSchema
	 */
	public static final String USE_INFORMATION_SCHEMA = "useInformationSchema";
	/**
	 * oracle 连接参数备注
	 */
	public static final String ORACLE_REMARKS = "remarks";
	/**
	 * 日志开始
	 */
	public static final String LOGGER_BEGINS = "Database design document generation begins 🚀";
	/**
	 * 日志结束
	 */
	public static final String LOGGER_COMPLETE = "Database design document generation is complete , time cost:%s second 🎇";
	/**
	 * 零
	 */
	public static final String ZERO = "0";
	/**
	 * N
	 */
	public static final String N = "N";
	/**
	 * Y
	 */
	public static final String Y = "Y";

	/**
	 * phoenix 命名空间
	 */
	public static final String PHOENIX_NAMESPACE_MAPPING = "phoenix.schema.isNamespaceMappingEnabled";

	/**
	 * phoenix 系统命名空间
	 */
	public static final String PHOENIX_SYS_NAMESPACE_MAPPING = "phoenix.schema.mapSystemTablesToNamespace";

}
