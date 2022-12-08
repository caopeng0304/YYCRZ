package com.sinosoft.ie.booster.visualdev.util;

/**
 * 数据库上下文切换
 *
 * @author booster开发平台组
 * @since 2021/3/16 10:47
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> CONTEXT_DB_NAME_HOLDER = new ThreadLocal<>();

	private static final ThreadLocal<String> CONTEXT_DB_ID_HOLDER = new ThreadLocal<>();

	/**
	 * 设置当前数据库
	 */
	public static void setDatasource(String dbId, String dbName) {
		CONTEXT_DB_NAME_HOLDER.set(dbName);
		CONTEXT_DB_ID_HOLDER.set(dbId);
	}

	/**
	 * 取得当前数据源Id
	 */
	public static String getDatasourceId() {
		return CONTEXT_DB_ID_HOLDER.get();
	}

	/**
	 * 取得当前数据源名称
	 */
	public static String getDatasourceName() {
		return CONTEXT_DB_NAME_HOLDER.get();
	}

	/**
	 * 清除上下文数据
	 */
	public static void clearDatasourceType() {
		CONTEXT_DB_NAME_HOLDER.remove();
		CONTEXT_DB_ID_HOLDER.remove();
	}
}
