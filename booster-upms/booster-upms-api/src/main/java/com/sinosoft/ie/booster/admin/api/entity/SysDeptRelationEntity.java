package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 部门关系表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_dept_relation")
@EqualsAndHashCode()
public class SysDeptRelationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	@ApiModelProperty(value = "祖先节点")
	private Long ancestor;

	/**
	 * 后代节点
	 */
	@ApiModelProperty(value = "后代节点")
	private Long descendant;

}
