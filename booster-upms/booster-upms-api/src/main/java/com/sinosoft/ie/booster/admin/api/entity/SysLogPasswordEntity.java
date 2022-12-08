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
 * 密码修改日志表
 * 记录系统重置密码和用户修改密码的操作
 * 用来实现“用户更换的密码不能和以往用过的密码相同”的需求
 *
 * @author haocy
 * @since 2021-12-01
 */
@Data
@TableName("sys_log_password")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "密码修改日志表")
public class SysLogPasswordEntity extends BaseEntity {

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "编号")
	private Long id;

	/**
	 * 操作类型 0系统重置 1用户修改
	 */
	@ApiModelProperty(value = "操作类型 0系统重置 1用户修改")
	private String operationType;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 修改后的密码
	 */
	@ApiModelProperty(value = "修改后的密码")
	private String password;

	/**
	 * 删除标记
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记")
	private String delFlag;
}
