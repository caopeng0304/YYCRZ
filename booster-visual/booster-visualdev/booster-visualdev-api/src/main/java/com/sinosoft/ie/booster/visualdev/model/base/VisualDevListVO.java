package com.sinosoft.ie.booster.visualdev.model.base;

import lombok.Data;

@Data
public class VisualDevListVO {
	private String id;
	private String fullName;
	private String enCode;
	private Integer state;

	private String type;
	private String dbLinkId;
	private String tables;
	private String description;
	private long createTime;
	private String createBy;
	private String category;
	private long updateTime;
	private String updateBy;
}
