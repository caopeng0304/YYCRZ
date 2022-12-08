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

/**
 * 字典项
 *
 * @author lengleng
 * @since 2019/03/19
 */
@Data
@TableName("sys_dict_item")
@ApiModel(value = "字典项")
@EqualsAndHashCode(callSuper = true)
public class SysDictItemEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "字典项id")
	private Long id;

	/**
	 * 父节点id
	 */
	@ApiModelProperty(value = "父节点id")
	private Long parentId;

	/**
	 * 所属字典类id
	 */
	@ApiModelProperty(value = "所属字典类id")
	private Long dictId;

	/**
	 * 数据值
	 */
	@ApiModelProperty(value = "数据值")
	private String value;

	/**
	 * 标签名
	 */
	@ApiModelProperty(value = "标签名")
	private String label;

	/**
	 * 类型
	 */
	@ApiModelProperty(value = "类型")
	private String type;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;

	/**
	 * 排序（升序）
	 */
	@ApiModelProperty(value = "排序值，默认升序")
	private Integer sort;

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
