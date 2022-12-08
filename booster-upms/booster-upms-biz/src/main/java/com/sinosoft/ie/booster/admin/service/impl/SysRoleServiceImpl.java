package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleMenuEntity;
import com.sinosoft.ie.booster.admin.mapper.SysRoleMapper;
import com.sinosoft.ie.booster.admin.mapper.SysRoleMenuMapper;
import com.sinosoft.ie.booster.admin.service.SysRoleService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

	private final SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysRoleEntity> findRolesByUserId(Long userId) {
		return baseMapper.listRolesByUserId(userId);
	}

	/**
	 * 通过角色ID，删除角色,并清空角色菜单缓存
	 *
	 * @param id
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeRoleById(Long id) {
		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenuEntity>update().lambda().eq(SysRoleMenuEntity::getRoleId, id));
		return this.removeById(id);
	}

	@Override
	public Boolean saveRole(SysRoleEntity roleEntity) {
		List<SysRoleEntity> duplicateEntries = this.list(
				Wrappers.<SysRoleEntity>lambdaQuery()
						.eq(SysRoleEntity::getRoleCode, roleEntity.getRoleCode())
		);
		Assert.isTrue(CollUtil.isEmpty(duplicateEntries), () -> String.format("角色标识“%s”已存在", roleEntity.getRoleCode()));
		return this.save(roleEntity);
	}

	@Override
	public Boolean updateRole(SysRoleEntity roleEntity) {
		List<SysRoleEntity> duplicateEntries = this.list(
				Wrappers.<SysRoleEntity>lambdaQuery()
						.eq(SysRoleEntity::getRoleCode, roleEntity.getRoleCode())
						.and(t -> t.not(e -> e.eq(SysRoleEntity::getRoleId, roleEntity.getRoleId())))
		);
		Assert.isTrue(CollUtil.isEmpty(duplicateEntries), () -> String.format("角色标识“%s”已存在", roleEntity.getRoleCode()));
		return this.updateById(roleEntity);
	}

}
