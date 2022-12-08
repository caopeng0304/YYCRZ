package com.sinosoft.ie.booster.admin.api.model.email;

import com.sinosoft.ie.booster.common.core.model.PaginationTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationEmail extends PaginationTime {
	private String type;
}
