package com.sinosoft.ie.booster.common.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
@Data
public class Pagination {
	private String keyword = "";
	private long pageSize = 20;
	private String sort = "desc";
	private String sidx = "";
	private long currentPage = 1;

	@JsonIgnore
	private long total;
	@JsonIgnore
	private long records;

	public <T> List<T> setData(List<T> data, long records) {
		this.total = records;
		return data;
	}
}
