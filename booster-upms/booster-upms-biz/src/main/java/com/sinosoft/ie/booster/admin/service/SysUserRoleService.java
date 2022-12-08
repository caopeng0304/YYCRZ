package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysUserRoleEntity;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @since 2017年12月7日 16:31:38
	 */
	Boolean removeRoleByUserId(Long userId);

}
