package com.sinosoft.ie.booster.common.core.model;

import lombok.Data;

import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
@Data
public class PageListVO<T> {
	private List<T> list;
	PaginationVO pagination;

}
