package com.sinosoft.ie.booster.workflow.model.flowtask;

import com.sinosoft.ie.booster.common.core.model.PaginationTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationFlowTask extends PaginationTime {
	/**
	 * 所属流程
	 **/
	private String flowId;
	/**
	 * 所属分类
	 **/
	private String flowCategory;
	private String createBy;
}
