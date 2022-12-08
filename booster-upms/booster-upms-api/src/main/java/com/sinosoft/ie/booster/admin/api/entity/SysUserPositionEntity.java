package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户岗位表
 * </p>
 *
 * @author haocy
 * @since 2022/9/14
 */
@Data
@TableName("sys_user_position")
@EqualsAndHashCode()
public class SysUserPositionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;

	/**
	 * 岗位ID
	 */
	@ApiModelProperty(value = "岗位id")
	private Long positionId;

}
