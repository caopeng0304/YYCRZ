package com.sinosoft.ie.booster.visualdev.model.base;

import lombok.Data;

@Data
public class VisualDevInfoVO {
	private String id;
	private String fullName;
	private String enCode;
	private String category;
	private String type;
	private String description;
	private String formData;
	private String columnData;
	private String tables;
	private String dbLinkId;
	private Integer state;
}
