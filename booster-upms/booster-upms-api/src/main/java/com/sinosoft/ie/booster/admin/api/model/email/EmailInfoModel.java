package com.sinosoft.ie.booster.admin.api.model.email;

import lombok.Data;

@Data
public class EmailInfoModel {
	private String fileId;
	private String fileName;
	private String fileSize;
	private String fileTime;
	private String fileState;
}
