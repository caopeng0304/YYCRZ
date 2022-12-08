package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典表
 *
 * @author lengleng
 * @since 2019/03/19
 */
@Data
@TableName("sys_dict")
@ApiModel(value = "字典类型")
@EqualsAndHashCode(callSuper = true)
public class SysDictEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "字典编号")
	private Long id;

	/**
	 * 类型
	 */
	@ApiModelProperty(value = "字典类型")
	private String type;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "字典描述")
	private String description;

	/**
	 * 是否是系统内置
	 */
	@ApiModelProperty(value = "是否系统内置")
	private String systemFlag;

	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注信息")
	private String remarks;

	/**
	 * 删除标记
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
