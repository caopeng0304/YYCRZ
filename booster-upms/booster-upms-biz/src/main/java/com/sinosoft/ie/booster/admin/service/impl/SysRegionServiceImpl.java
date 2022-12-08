package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.admin.api.model.RegionVO;
import com.sinosoft.ie.booster.admin.mapper.SysRegionMapper;
import com.sinosoft.ie.booster.admin.service.SysRegionService;
import com.sinosoft.ie.booster.common.core.exception.ValidateException;
import com.sinosoft.ie.booster.common.core.util.node.ForestNodeMerger;
import com.sinosoft.ie.booster.common.core.util.node.INode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 行政区划表 服务实现类
 *
 * @author haocy
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegionEntity> implements SysRegionService {
	public static final int PROVINCE_LEVEL = 1;
	public static final int CITY_LEVEL = 2;
	public static final int DISTRICT_LEVEL = 3;
	public static final int TOWN_LEVEL = 4;
	public static final int VILLAGE_LEVEL = 5;

	@Override
	public boolean submit(SysRegionEntity region) {
		Long cnt = baseMapper.selectCount(Wrappers.<SysRegionEntity>query().lambda().eq(SysRegionEntity::getCode, region.getCode()));
		if (cnt > 0) {
			return this.updateById(region);
		}
		// 设置祖区划编号
		SysRegionEntity parent = baseMapper.selectById(region.getParentCode());
		if (ObjectUtil.isNotEmpty(parent) || ObjectUtil.isNotEmpty(parent.getCode())) {
			String ancestors = parent.getAncestors() + StrPool.COMMA + parent.getCode();
			region.setAncestors(ancestors);
		}
		// 设置省、市、区、镇、村
		Integer level = region.getLevelCode();
		String code = region.getCode();
		String name = region.getName();
		if (level == PROVINCE_LEVEL) {
			region.setProvinceCode(code);
			region.setProvinceName(name);
		} else if (level == CITY_LEVEL) {
			region.setCityCode(code);
			region.setCityName(name);
		} else if (level == DISTRICT_LEVEL) {
			region.setDistrictCode(code);
			region.setDistrictName(name);
		} else if (level == TOWN_LEVEL) {
			region.setTownCode(code);
			region.setTownName(name);
		} else if (level == VILLAGE_LEVEL) {
			region.setVillageCode(code);
			region.setVillageName(name);
		}
		return this.save(region);
	}

	@Override
	public boolean removeRegion(String id) {
		Long cnt = baseMapper.selectCount(Wrappers.<SysRegionEntity>query().lambda().eq(SysRegionEntity::getParentCode, id));
		if (cnt > 0) {
			throw new ValidateException("请先删除子节点!");
		}
		return removeById(id);
	}

	@Override
	public List<INode> lazyList(String parentCode, Map<String, Object> param) {
		List<INode> list = baseMapper.lazyList(parentCode, param);
		return ForestNodeMerger.merge(list);
	}

	@Override
	public List<INode> lazyTree(String parentCode, Map<String, Object> param) {
		List<INode> list = baseMapper.lazyTree(parentCode, param);
		return ForestNodeMerger.merge(list);
	}

	@Override
    public RegionVO entityVO(SysRegionEntity region) {
		SysRegionEntity detail = getOne(Wrappers.query(region));
		RegionVO regionVO = Objects.requireNonNull(BeanUtil.copyProperties(detail, RegionVO.class));
		SysRegionEntity parentRegion = getById(detail.getParentCode());
		regionVO.setParentName(parentRegion.getName());
		return regionVO;
	}

	@Override
	public List<Tree<String>> listRegionTrees() {
		List<SysRegionEntity> regionList = this.list(Wrappers.emptyWrapper());
		List<TreeNode<String>> collect = regionList.stream()
				.filter(region -> !Objects.equals(region.getCode(), "00"))
				.map(region -> {
					TreeNode<String> treeNode = new TreeNode<>();
					treeNode.setId(region.getCode());
					treeNode.setParentId(region.getParentCode());
					treeNode.setName(region.getName());
					return treeNode;
				}).collect(Collectors.toList());
		return TreeUtil.build(collect, "00");
	}
}
