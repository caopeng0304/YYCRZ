package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptRelationEntity;
import com.sinosoft.ie.booster.admin.mapper.SysDeptRelationMapper;
import com.sinosoft.ie.booster.admin.service.SysDeptRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationMapper, SysDeptRelationEntity>
		implements SysDeptRelationService {

	private final SysDeptRelationMapper sysDeptRelationMapper;

	/**
	 * 维护部门关系
	 * @param sysDept 部门
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDeptRelation(SysDeptEntity sysDept) {
		// 增加部门关系表
		List<SysDeptRelationEntity> relationList = sysDeptRelationMapper.selectList(
				Wrappers.<SysDeptRelationEntity>query().lambda().eq(SysDeptRelationEntity::getDescendant, sysDept.getParentId()))
				.stream().peek(relation -> relation.setDescendant(sysDept.getDeptId())).collect(Collectors.toList());
		if (CollUtil.isNotEmpty(relationList)) {
			this.saveBatch(relationList);
		}

		// 自己也要维护到关系表中
		SysDeptRelationEntity own = new SysDeptRelationEntity();
		own.setDescendant(sysDept.getDeptId());
		own.setAncestor(sysDept.getDeptId());
		sysDeptRelationMapper.insert(own);
	}

	/**
	 * 通过ID删除部门关系
     * @param id
     */
	@Override
	public void removeDeptRelationById(Long id) {
		baseMapper.deleteDeptRelationsById(id);
	}

	/**
	 * 更新部门关系
	 * @param relation
	 */
	@Override
	public void updateDeptRelation(SysDeptRelationEntity relation) {
		baseMapper.updateDeptRelations(relation);
	}

}
