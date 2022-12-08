package com.sinosoft.ie.booster.visualdev.model.base;

import lombok.Data;

import java.util.List;

@Data
public class VisualDevTreeVO {
	private String id;
	private String fullName;
	private Boolean hasChildren;
	private List<VisualDevTreeChildModel> children;
}
