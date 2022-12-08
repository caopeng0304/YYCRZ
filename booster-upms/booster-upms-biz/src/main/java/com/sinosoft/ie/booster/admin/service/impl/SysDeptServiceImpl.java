package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptRelationEntity;
import com.sinosoft.ie.booster.admin.mapper.SysDeptMapper;
import com.sinosoft.ie.booster.admin.service.SysDeptRelationService;
import com.sinosoft.ie.booster.admin.service.SysDeptService;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {

	private final SysDeptRelationService sysDeptRelationService;

	/**
	 * 添加信息部门
	 * @param dept 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDeptEntity dept) {
		SysDeptEntity sysDept = new SysDeptEntity();
		BeanUtils.copyProperties(dept, sysDept);
		this.save(sysDept);
		sysDeptRelationService.saveDeptRelation(sysDept);
		return Boolean.TRUE;
	}

	/**
	 * 删除部门
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeDeptById(Long id) {
		// 级联删除部门
		List<Long> idList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelationEntity>query().lambda().eq(SysDeptRelationEntity::getAncestor, id)).stream()
				.map(SysDeptRelationEntity::getDescendant).collect(Collectors.toList());

		if (CollUtil.isNotEmpty(idList)) {
			this.removeByIds(idList);
		}

		// 删除部门级联关系
		sysDeptRelationService.removeDeptRelationById(id);
		return Boolean.TRUE;
	}

	/**
	 * 更新部门
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDeptEntity sysDept) {
		// 更新部门状态
		this.updateById(sysDept);
		// 更新部门关系
		SysDeptRelationEntity relation = new SysDeptRelationEntity();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRelation(relation);
		return Boolean.TRUE;
	}

	/**
	 * 查询全部部门树
	 * @return 树
	 */
	@Override
	public List<Tree<Long>> listDeptTrees() {
		return getDeptTree(this.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 查询用户部门树
	 * @return
	 */
	@Override
	public List<Tree<Long>> listCurrentUserDeptTrees() {
		Long deptId = SecurityUtils.getUser().getDeptId();
		List<Long> descendantIdList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelationEntity>query().lambda().eq(SysDeptRelationEntity::getAncestor, deptId)).stream()
				.map(SysDeptRelationEntity::getDescendant).collect(Collectors.toList());

		List<SysDeptEntity> deptList = baseMapper.selectBatchIds(descendantIdList);
		return getDeptTree(deptList);
	}

	/**
	 * 构建部门树
	 * @param depts 部门
	 * @return
	 */
	private List<Tree<Long>> getDeptTree(List<SysDeptEntity> depts) {
		List<TreeNode<Long>> collect = depts.stream()
				.filter(dept -> dept.getDeptId().intValue() != dept.getParentId())
				.sorted(Comparator.comparingInt(SysDeptEntity::getSort)).map(dept -> {
					TreeNode<Long> treeNode = new TreeNode<>();
					treeNode.setId(dept.getDeptId());
					treeNode.setParentId(dept.getParentId());
					treeNode.setName(dept.getName());
					treeNode.setWeight(dept.getSort());
					// 扩展属性
					Map<String, Object> extra = new HashMap<>(4);
					extra.put("createTime", dept.getCreateTime());
					treeNode.setExtra(extra);
					return treeNode;
				}).collect(Collectors.toList());

		return TreeUtil.build(collect, 0L);
	}

}
