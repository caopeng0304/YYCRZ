package com.sinosoft.ie.booster.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysUserRoleEntity;
import com.sinosoft.ie.booster.admin.mapper.SysUserRoleMapper;
import com.sinosoft.ie.booster.admin.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @since 2017年12月7日 16:31:38
	 */
	@Override
	public Boolean removeRoleByUserId(Long userId) {
		return baseMapper.deleteByUserId(userId);
	}

}
