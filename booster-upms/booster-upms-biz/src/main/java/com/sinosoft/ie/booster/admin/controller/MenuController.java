package com.sinosoft.ie.booster.admin.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysMenuEntity;
import com.sinosoft.ie.booster.admin.service.SysMenuService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.log.annotation.SysLog;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @since 2017/10/31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class MenuController {

	private final SysMenuService sysMenuService;

	/**
	 * 返回当前用户的树形菜单集合
	 *
	 * @param parentId   父节点ID
	 * @param clientType 客户端类型
	 * @return 当前用户的树形菜单
	 */
	@GetMapping
	public R<List<Tree<Long>>> getUserMenu(Long parentId, String clientType) {

		// 获取符合条件的菜单
		Set<SysMenuEntity> all = new HashSet<>();
		SecurityUtils.getRoles().forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
		return R.ok(sysMenuService.filterMenu(all, parentId, clientType));
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @param lazy       是否是懒加载
	 * @param parentId   父节点ID
	 * @param clientType 客户端类型
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R<List<Tree<Long>>> getTree(boolean lazy, Long parentId, String clientType) {
		return R.ok(sysMenuService.treeMenu(lazy, parentId, clientType));
	}

	/**
	 * 返回角色的菜单集合
	 *
	 * @param roleId     角色ID
	 * @param clientType 客户端类型
	 * @return 属性集合
	 */
	@GetMapping("/tree/{roleId}")
	public R<List<Long>> getRoleTree(@PathVariable Long roleId, String clientType) {
		//默认为Web端
		clientType = StrUtil.isEmpty(clientType) ? "0" : clientType;
		String finalClientType = clientType;
		return R.ok(
				sysMenuService.findMenuByRoleId(roleId)
						.stream()
						.filter(r -> finalClientType.equals(r.getClientType()))
						.map(SysMenuEntity::getMenuId)
						.collect(Collectors.toList())
		);
	}

	/**
	 * 通过ID查询菜单的详细信息
	 *
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public R<SysMenuEntity> getById(@PathVariable Long id) {
		return R.ok(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 *
	 * @param sysMenu 菜单信息
	 * @return 含ID 菜单信息
	 */
	@SysLog("新增菜单")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_add')")
	public R<SysMenuEntity> save(@Valid @RequestBody SysMenuEntity sysMenu) {
		sysMenuService.save(sysMenu);
		return R.ok(sysMenu);
	}

	/**
	 * 删除菜单
	 *
	 * @param id 菜单ID
	 * @return success/false
	 */
	@SysLog("删除菜单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_menu_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(sysMenuService.removeMenuById(id));
	}

	/**
	 * 更新菜单
	 *
	 * @param sysMenu
	 * @return
	 */
	@SysLog("更新菜单")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_edit')")
	public R<Boolean> update(@Valid @RequestBody SysMenuEntity sysMenu) {
		return R.ok(sysMenuService.updateMenuById(sysMenu));
	}
}
