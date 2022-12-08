package com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode;

import lombok.Data;

/**
 * 解析引擎
 *
 * @author booster开发平台组
 * @since 2021/3/15 9:12
 */
@Data
public class FormOperates {
	/**
	 * 可读
	 **/
	private boolean read;
	/**
	 * 名称
	 **/
	private String name;
	/**
	 * 字段
	 **/
	private String id;
	/**
	 * 可写
	 **/
	private boolean write;
}
