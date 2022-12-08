package com.sinosoft.ie.booster.common.database.metadata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DataModel
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/6/17 22:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataModel extends DatabaseModel {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 组织
	 */
	private String organization;
	/**
	 * url
	 */
	private String organizationUrl;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 描述
	 */
	private String description;
}
