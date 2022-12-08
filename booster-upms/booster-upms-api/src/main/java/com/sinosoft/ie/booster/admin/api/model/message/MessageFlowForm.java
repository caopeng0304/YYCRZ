package com.sinosoft.ie.booster.admin.api.model.message;

import lombok.Data;

import java.util.List;

@Data
public class MessageFlowForm {

	private List<String> toUserIds;
	private String title;
	private String bodyText;

}
