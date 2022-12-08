package com.sinosoft.ie.booster.visualdev.model.dbtable;


import com.sinosoft.ie.booster.common.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DbTableDataForm extends Pagination {
	private String field;
}
