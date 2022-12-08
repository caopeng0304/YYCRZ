package com.sinosoft.ie.booster.admin.api.model;

import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Data
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;

	/**
	 * 锁定标记
	 */
	private String lockFlag;

	/**
	 * 简介
	 */
	private String phone;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 部门ID
	 */
	private Long deptId;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 上级主管
	 */
	private String manager;

	/**
	 * 门户Id
	 */
	private String portalId;

	/**
	 * 上次密码更新时间
	 */
	@ApiModelProperty(value = "上次密码更新时间")
	private LocalDateTime lastPasswordUpdated;

	/**
	 * 角色列表
	 */
	private List<SysRoleEntity> roleList;

	/**
	 * 岗位列表
	 */
	private List<SysPositionEntity> positionList;

}
