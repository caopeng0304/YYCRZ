package com.sinosoft.ie.booster.visualdev.model.base;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * 多表开发配置基类
 */
@Data
public class BaseTemplateModel {

	/**
	 * 转换成驼峰形式
	 */
	public String toUpperAttr(String columnName) {
		if (columnName == null) {
			return null;
		}
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_" , "" );
	}

	/**
	 * 转换成首字母小写形式
	 */
	public String toLowerAttr(String columnName) {
		if (columnName == null) {
			return null;
		}
		String attrName = toUpperAttr(columnName);
		return StringUtils.uncapitalize(attrName);
	}
}
