package com.sinosoft.ie.booster.common.core.model;

import lombok.Data;

/**
 * 附件模型
 *
 * @author booster开发平台组
 * @since 2019年9月26日 上午9:18
 */
@Data
public class FileModel {
	private String fileId;
	private String fileName;
	private String fileSize;
	private String fileTime;
	private String fileState;
	private String fileType;
}
