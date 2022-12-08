package com.sinosoft.ie.booster.admin.api.model;

import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUserEntity {

	/**
	 * 角色ID
	 */
	private List<Long> role;

	/**
	 * 岗位ID
	 */
	private List<Long> position;

	/**
	 * 新密码
	 */
	private String newpassword1;

}
