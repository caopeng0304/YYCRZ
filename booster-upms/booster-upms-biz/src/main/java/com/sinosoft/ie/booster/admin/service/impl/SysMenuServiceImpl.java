package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysMenuEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleMenuEntity;
import com.sinosoft.ie.booster.admin.mapper.SysMenuMapper;
import com.sinosoft.ie.booster.admin.mapper.SysRoleMenuMapper;
import com.sinosoft.ie.booster.admin.service.SysMenuService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.MenuTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

	private final SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
	public List<SysMenuEntity> findMenuByRoleId(Long roleId) {
		return baseMapper.listMenusByRoleId(roleId);
	}

	/**
	 * 级联删除菜单
	 *
	 * @param id 菜单ID
	 * @return true成功, false失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean removeMenuById(Long id) {
		// 查询父节点为当前节点的节点
		List<SysMenuEntity> menuList = this.list(Wrappers.<SysMenuEntity>query().lambda().eq(SysMenuEntity::getParentId, id));

		Assert.isTrue(CollUtil.isEmpty(menuList), "菜单含有下级不能删除");

		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenuEntity>query().lambda().eq(SysRoleMenuEntity::getMenuId, id));
		// 删除当前菜单及其子菜单
		return this.removeById(id);
	}

	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean updateMenuById(SysMenuEntity sysMenu) {
		return this.updateById(sysMenu);
	}

	/**
	 * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
	 *
	 * @param lazy       是否是懒加载
	 * @param parentId   父节点ID
	 * @param clientType 客户端类型
	 * @return
	 */
	@Override
	public List<Tree<Long>> treeMenu(boolean lazy, Long parentId, String clientType) {
		//默认为Web端
		clientType = StrUtil.isEmpty(clientType) ? "0" : clientType;
		if (!lazy) {
			List<TreeNode<Long>> collect = baseMapper
					.selectList(
							Wrappers.<SysMenuEntity>lambdaQuery()
									.eq(SysMenuEntity::getClientType, clientType)
									.orderByAsc(SysMenuEntity::getSort)
					).stream().map(getNodeFunction()).collect(Collectors.toList());

			return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
		}

		Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		List<TreeNode<Long>> collect = baseMapper
				.selectList(
						Wrappers.<SysMenuEntity>lambdaQuery()
								.eq(SysMenuEntity::getParentId, parent)
								.eq(SysMenuEntity::getClientType, clientType)
								.orderByAsc(SysMenuEntity::getSort)
				).stream().map(getNodeFunction()).collect(Collectors.toList());

		return TreeUtil.build(collect, parent);
	}

	/**
	 * 查询菜单
	 *
	 * @param all        全部菜单
	 * @param parentId   父节点ID
	 * @param clientType 客户端类型
	 * @return
	 */
	@Override
	public List<Tree<Long>> filterMenu(Set<SysMenuEntity> all, Long parentId, String clientType) {
		//默认为Web端
		clientType = StrUtil.isEmpty(clientType) ? "0" : clientType;
		String finalClientType = clientType;
		List<TreeNode<Long>> collect = all.stream()
				.filter(menu ->
						(MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType())
								|| MenuTypeEnum.FUNCTION_MENU.getType().equals(menu.getType())
								|| MenuTypeEnum.SCREEN_MENU.getType().equals(menu.getType()))
								&& finalClientType.equals(menu.getClientType())
				)
				.map(getNodeFunction())
				.collect(Collectors.toList());
		Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		return TreeUtil.build(collect, parent);
	}

	@NotNull
	private Function<SysMenuEntity, TreeNode<Long>> getNodeFunction() {
		return menu -> {
			TreeNode<Long> node = new TreeNode<>();
			node.setId(menu.getMenuId());
			node.setName(menu.getName());
			node.setParentId(menu.getParentId());
			node.setWeight(menu.getSort());
			// 扩展属性
			Map<String, Object> extra = new HashMap<>();
			extra.put("icon", menu.getIcon());
			extra.put("path", menu.getPath());
			extra.put("type", menu.getType());
			extra.put("clientType", menu.getClientType());
			extra.put("moduleId", menu.getModuleId());
			extra.put("permission", menu.getPermission());
			extra.put("label", menu.getName());
			extra.put("sort", menu.getSort());
			extra.put("keepAlive", menu.getKeepAlive());
			extra.put("encode", menu.getEncode());
			node.setExtra(extra);
			return node;
		};
	}

}
