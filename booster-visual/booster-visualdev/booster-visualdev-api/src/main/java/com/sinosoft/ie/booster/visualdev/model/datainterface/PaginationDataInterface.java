package com.sinosoft.ie.booster.visualdev.model.datainterface;

import com.sinosoft.ie.booster.common.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationDataInterface extends Pagination {
	private String categoryId;
}
