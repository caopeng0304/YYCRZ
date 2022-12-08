package com.sinosoft.ie.booster.admin.api.model.message;

import lombok.Data;

@Data
public class MessageInfoVO {
	private String id;
	private String title;
	private Integer type;
	private long updateTime;
	private String createBy;
	private Integer isRead;
}
