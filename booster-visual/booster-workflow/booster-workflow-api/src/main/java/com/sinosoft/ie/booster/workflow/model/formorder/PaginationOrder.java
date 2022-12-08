package com.sinosoft.ie.booster.workflow.model.formorder;

import com.sinosoft.ie.booster.common.core.model.PaginationTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单信息
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationOrder extends PaginationTime {
	private String enabledFlag;
}
