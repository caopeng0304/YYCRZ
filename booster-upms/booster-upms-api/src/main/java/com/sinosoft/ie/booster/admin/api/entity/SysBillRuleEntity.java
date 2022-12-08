package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 单据规则
 *
 * @author booster code generator
 * @since 2021-08-13 16:44:44
 */
@Data
@TableName("sys_bill_rule")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "单据规则")
public class SysBillRuleEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 单据主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "单据主键")
	private Long id;
	/**
	 * 单据名称
	 */
	@NotBlank(message = "单据名称必须填写")
	@ApiModelProperty(value = "单据名称")
	private String fullName;
	/**
	 * 单据编号
	 */
	@NotBlank(message = "单据编号必须填写")
	@ApiModelProperty(value = "单据编号")
	private String encode;
	/**
	 * 单据前缀
	 */
	@NotBlank(message = "单据前缀必须填写")
	@ApiModelProperty(value = "单据前缀")
	private String prefix;
	/**
	 * 日期格式
	 */
	@NotBlank(message = "请选择日期格式")
	@ApiModelProperty(value = "日期格式")
	private String dateFormat;
	/**
	 * 流水位数
	 */
	@NotNull(message = "流水位数必须填写")
	@ApiModelProperty(value = "流水位数")
	private Integer digit;
	/**
	 * 当前流水号
	 */
	@ApiModelProperty(value = "当前流水号")
	private Integer thisNumber;
	/**
	 * 输出流水号
	 */
	@ApiModelProperty(value = "输出流水号")
	private String outputNumber;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 排序码
	 */
	@ApiModelProperty(value = "排序码")
	private Integer sort;
	/**
	 * 删除标记
	 */
	@ApiModelProperty(value = "删除标记")
	@TableLogic
	private String delFlag;
}
