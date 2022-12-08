package com.sinosoft.ie.booster.workflow.model.flowtask;

import lombok.Data;

import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowTableModel {

	private String relationField;
	private String relationTable;
	private String table;
	private String tableName;
	private String tableField;
	private String typeId;
	private List<FlowFieldsModel> fields;

}
