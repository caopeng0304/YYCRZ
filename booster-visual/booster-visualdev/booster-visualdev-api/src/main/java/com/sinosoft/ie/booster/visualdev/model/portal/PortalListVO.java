package com.sinosoft.ie.booster.visualdev.model.portal;

import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021-10-21 14:23:30
 */
@Data
public class PortalListVO {
	private String id;
	private String fullName;
	private String enCode;
	private String description;
	private long createTime;
	private String createBy;
	private String category;
	private long updateTime;
	private String updateBy;
	private String enabledFlag;
	private Long sort;
}
