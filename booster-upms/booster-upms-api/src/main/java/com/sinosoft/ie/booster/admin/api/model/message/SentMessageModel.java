package com.sinosoft.ie.booster.admin.api.model.message;

import lombok.Data;

import java.util.List;

@Data
public class SentMessageModel {
	private List<String> toUserNames;
	private String title;
	private String bodyText;
}
