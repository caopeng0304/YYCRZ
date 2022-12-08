package com.sinosoft.ie.booster.visualdev.model.dbtable;

import lombok.Data;

import java.util.List;

@Data
public class DbTableCrForm {

	private DbTableForm tableInfo;

	private List<DbTableFieldForm> tableFieldList;
}
