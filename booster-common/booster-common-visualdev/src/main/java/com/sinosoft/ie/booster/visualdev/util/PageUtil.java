package com.sinosoft.ie.booster.visualdev.util;

import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
public class PageUtil {
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param pageSize
	 * @param list
	 * @return
	 */
	public static List getListPage(int page, int pageSize, List list) {
		if (list == null || list.size() == 0) {
			return list;
		}
		int totalCount = list.size();
		page = page - 1;
		int fromIndex = page * pageSize;
		if (fromIndex >= totalCount) {
			return list;
		}
		int toIndex = ((page + 1) * pageSize);
		if (toIndex > totalCount) {
			toIndex = totalCount;
		}
		return list.subList(fromIndex, toIndex);
	}
}
