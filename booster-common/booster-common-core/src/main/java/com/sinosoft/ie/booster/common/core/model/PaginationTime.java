package com.sinosoft.ie.booster.common.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationTime extends Pagination {
	private String startTime;
	private String endTime;
//    private String type;
}
