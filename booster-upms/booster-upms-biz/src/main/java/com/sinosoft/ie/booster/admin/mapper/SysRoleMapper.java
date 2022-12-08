package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 * @return
	 */
	List<SysRoleEntity> listRolesByUserId(Long userId);

}
