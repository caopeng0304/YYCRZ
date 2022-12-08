package com.sinosoft.ie.booster.visualdev.model.flowdynamic;

import lombok.Data;

/**
 * 解析引擎
 *
 * @author booster开发平台组
 * @since 2021/3/15 9:19
 */
@Data
public class FormAllModel {
	/**
	 * 所有模板的标签 row(栅格)、card(卡片)、table(子表)、mast(主表)
	 **/
	private String boosKey;
	/**
	 * 是否是结束标签 0.不是 1.是
	 **/
	private String isEnd = "0";
	/**
	 * 主表数据
	 **/
	private FormColumnModel formColumnModel;
	/**
	 * 子表的数据
	 **/
	private FormColumnTableModel childList;
	/**
	 * 栅格和卡片数据
	 **/
	private FormModel formModel;

}
