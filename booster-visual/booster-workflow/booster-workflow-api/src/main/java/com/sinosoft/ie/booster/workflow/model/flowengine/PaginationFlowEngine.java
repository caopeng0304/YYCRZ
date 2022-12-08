package com.sinosoft.ie.booster.workflow.model.flowengine;

import com.sinosoft.ie.booster.common.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationFlowEngine extends Pagination {
	private String formType;
	private String enabledFlag;
}
