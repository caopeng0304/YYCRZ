package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleMenuEntity;
import com.sinosoft.ie.booster.admin.mapper.SysRoleMenuMapper;
import com.sinosoft.ie.booster.admin.service.SysRoleMenuService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements SysRoleMenuService {

	private final CacheManager cacheManager;

	/**
	 * @param role
	 * @param roleId 角色
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.MENU_DETAILS, key = "#roleId + '_menu'")
	public Boolean saveRoleMenus(String role, Long roleId, String menuIds) {
		this.remove(Wrappers.<SysRoleMenuEntity>query().lambda().eq(SysRoleMenuEntity::getRoleId, roleId));

		if (StrUtil.isBlank(menuIds)) {
			return Boolean.TRUE;
		}
		List<SysRoleMenuEntity> roleMenuList = Arrays.stream(menuIds.split(",")).map(menuId -> {
			SysRoleMenuEntity roleMenu = new SysRoleMenuEntity();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(Convert.toLong(menuId));
			return roleMenu;
		}).collect(Collectors.toList());

		// 清空userinfo
		Objects.requireNonNull(cacheManager.getCache(CacheConstants.USER_DETAILS)).clear();
		return this.saveBatch(roleMenuList);
	}

}
