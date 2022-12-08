package com.sinosoft.ie.booster.visualdev.model.portal;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class EmailVO {
	private String id;
	@JSONField(name = "subject")
	private String fullName;
	private Long createTime;
}
