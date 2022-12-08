package com.sinosoft.ie.booster.admin.api.model.message;

import com.sinosoft.ie.booster.common.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationMessage extends Pagination {
	private String type;
}
