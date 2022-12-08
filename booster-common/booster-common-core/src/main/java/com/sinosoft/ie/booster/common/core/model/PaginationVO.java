package com.sinosoft.ie.booster.common.core.model;

import lombok.Data;

/**
 * 需要分页的模型
 *
 * @author booster开发平台组
 * @since 2021-03-15 09:50
 */
@Data
public class PaginationVO {
	private Long currentPage;
	private Long pageSize;
	private Integer total;
}
