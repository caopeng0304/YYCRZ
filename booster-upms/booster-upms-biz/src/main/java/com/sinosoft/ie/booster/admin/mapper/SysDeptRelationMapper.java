package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Mapper
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelationEntity> {

	/**
	 * 删除部门关系表数据
	 * @param id 部门ID
	 */
	void deleteDeptRelationsById(Long id);

	/**
	 * 更改部分关系表数据
	 * @param deptRelation
	 */
	void updateDeptRelations(SysDeptRelationEntity deptRelation);

}
