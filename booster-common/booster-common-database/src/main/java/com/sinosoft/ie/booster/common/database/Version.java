package com.sinosoft.ie.booster.common.database;

import java.io.Serializable;

/**
 * Screw Version
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/28 15:02
 */
public class Version implements Serializable {
	private Version() {
	}

	/**
	 * 获取版本号
	 *
	 * @return {@link String} 版本号
	 */
	public static String getVersion() {
		Package pkg = Version.class.getPackage();
		return (pkg != null ? pkg.getImplementationVersion() : null);
	}
}
