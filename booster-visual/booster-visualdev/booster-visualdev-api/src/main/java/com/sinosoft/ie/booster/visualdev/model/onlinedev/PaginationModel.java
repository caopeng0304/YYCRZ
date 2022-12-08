package com.sinosoft.ie.booster.visualdev.model.onlinedev;


import com.sinosoft.ie.booster.common.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationModel extends Pagination {
	private String json;
}
