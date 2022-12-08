package com.sinosoft.ie.booster.admin.api.model.message;

import lombok.Data;

@Data
public class NoticeInfoVO {
	private String id;
	private String title;
	private String bodyText;
	private String createBy;
	private long createTime;
}
