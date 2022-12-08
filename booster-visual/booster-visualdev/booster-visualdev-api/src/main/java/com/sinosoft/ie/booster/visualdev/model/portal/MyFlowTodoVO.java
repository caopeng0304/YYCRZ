package com.sinosoft.ie.booster.visualdev.model.portal;

import lombok.Data;

@Data
public class MyFlowTodoVO {
	private String id;
	private String enabledFlag;
	private Long startTime;
	private Long endTime;
	private String content;
}
