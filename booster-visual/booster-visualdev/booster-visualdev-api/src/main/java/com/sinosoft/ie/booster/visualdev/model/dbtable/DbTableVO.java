package com.sinosoft.ie.booster.visualdev.model.dbtable;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DbTableVO {
	private DbTableInfoVO tableInfo;
	private List<DbTableFieldVO> tableFieldList;
}
