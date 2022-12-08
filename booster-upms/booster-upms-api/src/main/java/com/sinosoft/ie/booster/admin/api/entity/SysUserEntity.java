package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_user_info")
@EqualsAndHashCode(callSuper = true)
public class SysUserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "user_id", type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键id")
	private Long userId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;

	/**
	 * 锁定标记
	 */
	@ApiModelProperty(value = "锁定标记")
	private String lockFlag;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像地址")
	private String avatar;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "用户所属部门id")
	private Long deptId;

	/**
	 * 上级主管
	 */
	@ApiModelProperty(value = "上级主管")
	private String manager;

	/**
	 * 0-正常，1-删除
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 微信openid
	 */
	@ApiModelProperty(value = "微信openid")
	private String wxOpenid;

	/**
	 * QQ openid
	 */
	@ApiModelProperty(value = "QQ openid")
	private String qqOpenid;

	/**
	 * 码云唯一标识
	 */
	@ApiModelProperty(value = "码云唯一标识")
	private String giteeLogin;

	/**
	 * 开源中国唯一标识
	 */
	@ApiModelProperty(value = "开源中国唯一标识")
	private String oscId;

	/**
	 * 门户Id
	 */
	@ApiModelProperty(value = "门户Id")
	private String portalId;

	/**
	 * 上次密码更新时间，重置密码后应设置为null，表示初始口令，用户首次登录强制修改密码用
	 * 用户更新初始密码后应更新此字段，用于登录时判断密码是否到期需要强制更新
	 */
	@ApiModelProperty(value = "上次密码更新时间")
	private LocalDateTime lastPasswordUpdated;
}
