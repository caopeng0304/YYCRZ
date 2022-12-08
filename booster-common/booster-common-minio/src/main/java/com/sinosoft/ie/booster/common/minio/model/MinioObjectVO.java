package com.sinosoft.ie.booster.common.minio.model;

import io.minio.ObjectStat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 存储对象的元数据
 *
 * @author lengleng
 */
@Data
@AllArgsConstructor
public class MinioObjectVO {
	private String bucketName;
	private String name;
	private Date createdTime;
	private Long length;
	private String etag;
	private String contentType;
	private Map<String, List<String>> httpHeaders;

	public MinioObjectVO(ObjectStat os) {
		this.bucketName = os.bucketName();
		this.name = os.name();
		this.createdTime = os.createdTime();
		this.length = os.length();
		this.etag = os.etag();
		this.contentType = os.contentType();
		this.httpHeaders = os.httpHeaders();
	}

}
