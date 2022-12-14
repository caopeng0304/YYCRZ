package com.sinosoft.ie.booster.visualdev.model.portal;

import lombok.Data;

import java.util.List;

@Data
public class PortalSelectVO {
	private String id;
	private String fullName;
	private List<PortalSelectVO> children;
	private boolean hasChildren;
	private String parentId;
}
