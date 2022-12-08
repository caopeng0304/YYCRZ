package com.sinosoft.ie.booster.visualdev.model.portal;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class NoticeVO {
	private String id;
	@JSONField(name = "title")
	private String fullName;
	private Long createTime;

}
