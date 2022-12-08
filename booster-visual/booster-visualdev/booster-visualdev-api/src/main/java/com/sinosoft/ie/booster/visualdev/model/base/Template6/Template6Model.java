package com.sinosoft.ie.booster.visualdev.model.base.Template6;

import com.sinosoft.ie.booster.visualdev.model.base.TableModel;
import com.sinosoft.ie.booster.visualdev.model.base.BaseTemplateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 多表开发配置
 *
 * @author booster开发平台组
 * @since 2021/3/16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Template6Model extends BaseTemplateModel {
	/**
	 * 版本
	 */
	private String version = "V1.0.0";
	/**
	 * 版权
	 */
	private String copyright;
	/**
	 * 创建人员
	 */
	private String createUser;
	/**
	 * 创建日期
	 */
	private String createDate;
	/**
	 * 功能描述
	 */
	private String description;
	/**
	 * 子类功能名称
	 */
	private String subClassName;
	/**
	 * 主类功能名称
	 */
	private String className;

	/**
	 * 列表主表 - 字段集合
	 */
	private List<ColumnListField> columnListFields;


	private String serviceDirectory;


	/**
	 * 数据关联 - 集合
	 */
	private List<TableModel> dbTableRelation;
}
