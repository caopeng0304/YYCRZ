package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleMenuEntity;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

	/**
	 * 更新角色菜单
	 * @param role
	 * @param roleId 角色
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	Boolean saveRoleMenus(String role, Long roleId, String menuIds);

}
