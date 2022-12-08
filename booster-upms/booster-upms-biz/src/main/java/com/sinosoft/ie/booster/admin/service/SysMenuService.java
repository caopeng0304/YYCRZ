package com.sinosoft.ie.booster.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysMenuEntity;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysMenuService extends IService<SysMenuEntity> {

	/**
	 * 通过角色编号查询URL 权限
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	List<SysMenuEntity> findMenuByRoleId(Long roleId);

	/**
	 * 级联删除菜单
	 * @param id 菜单ID
	 * @return true成功, false失败
	 */
	Boolean removeMenuById(Long id);

	/**
	 * 更新菜单信息
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenuEntity sysMenu);

	/**
	 * 构建树
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @param clientType 客户端类型
	 * @return
	 */
	List<Tree<Long>> treeMenu(boolean lazy, Long parentId, String clientType);

	/**
	 * 查询菜单
	 * @param menuSet
	 * @param parentId
	 * @param clientType
	 * @return
	 */
	List<Tree<Long>> filterMenu(Set<SysMenuEntity> menuSet, Long parentId, String clientType);

}
