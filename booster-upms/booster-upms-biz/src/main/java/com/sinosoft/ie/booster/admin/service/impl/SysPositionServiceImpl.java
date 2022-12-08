package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserPositionEntity;
import com.sinosoft.ie.booster.admin.mapper.SysPositionMapper;
import com.sinosoft.ie.booster.admin.api.model.position.PositionPagination;
import com.sinosoft.ie.booster.admin.service.SysPositionService;
import com.sinosoft.ie.booster.admin.service.SysUserPositionService;
import com.sinosoft.ie.booster.visualdev.constant.type.SortType;
import com.sinosoft.ie.booster.visualdev.util.CacheKeyUtil;
import com.sinosoft.ie.booster.visualdev.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * sys_position
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-09-14 15:10:15
 */
@Service
public class SysPositionServiceImpl extends ServiceImpl<SysPositionMapper, SysPositionEntity> implements SysPositionService {

	@Autowired
	private SysUserPositionService userPositionService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private CacheKeyUtil cacheKeyUtil;

	@Override
	public List<SysPositionEntity> getList() {
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByAsc(SysPositionEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public List<SysPositionEntity> getList(PositionPagination paginationPosition) {
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		//组织机构
		if (!StrUtil.isEmpty(paginationPosition.getDeptId())) {
			String[] organizeIds = paginationPosition.getDeptId().split(",");
			if (organizeIds.length > 0) {
				queryWrapper.lambda().in(SysPositionEntity::getDeptId, organizeIds);
			}
		}
		//关键字（名称、编码）
		if (!StrUtil.isEmpty(paginationPosition.getKeyword())) {
			queryWrapper.lambda().and(
					t -> t.like(SysPositionEntity::getFullName, paginationPosition.getKeyword())
							.or().like(SysPositionEntity::getEncode, paginationPosition.getKeyword())
			);
		}
		//排序
		if (SortType.ASC.equalsIgnoreCase(paginationPosition.getSort())) {
			queryWrapper.lambda().orderByAsc(SysPositionEntity::getSort).orderByAsc(SysPositionEntity::getCreateTime);
		} else {
			queryWrapper.lambda().orderByDesc(SysPositionEntity::getSort).orderByDesc(SysPositionEntity::getCreateTime);
		}
		Page<SysPositionEntity> page = new Page<>(paginationPosition.getCurrentPage(), paginationPosition.getPageSize());
		IPage<SysPositionEntity> userIPage = this.page(page, queryWrapper);
		return paginationPosition.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public List<SysPositionEntity> getListByUserName(String userName) {
		return this.baseMapper.getListByUserName(userName);
	}

	@Override
	public List<String> getUserPositionByIds(String ids) {
		List<String> sqlInIds = Arrays.stream(ids.split(",")).map(r -> "'" + r + "'").collect(Collectors.toList());
		return this.baseMapper.getUserPositionByIds(String.join(",", sqlInIds));
	}

	@Override
	public SysPositionEntity getInfo(String id) {
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysPositionEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public boolean isExistByFullName(String fullName, String id) {
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysPositionEntity::getFullName, fullName);
		if (!StrUtil.isEmpty(id)) {
			queryWrapper.lambda().ne(SysPositionEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public boolean isExistByEnCode(String enCode, String id) {
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysPositionEntity::getEncode, enCode);
		if (!StrUtil.isEmpty(id)) {
			queryWrapper.lambda().ne(SysPositionEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public void create(SysPositionEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, SysPositionEntity entity) {
		entity.setId(Long.valueOf(id));
		return this.updateById(entity);
	}

	@Override
	@Transactional
	public void delete(SysPositionEntity entity) {
		this.removeById(entity.getId());
		QueryWrapper<SysUserPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysUserPositionEntity::getPositionId, entity.getId());
		userPositionService.remove(queryWrapper);
	}

	@Override
	@Transactional
	public boolean first(String id) {
		boolean isOk = false;
		//获取要上移的那条数据的信息
		SysPositionEntity upEntity = this.getById(id);
		Long upSortCode = upEntity.getSort() == null ? 0L : upEntity.getSort();
		//查询上几条记录
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda()
				.lt(SysPositionEntity::getSort, upSortCode)
				.eq(SysPositionEntity::getDeptId, upEntity.getDeptId())
				.orderByDesc(SysPositionEntity::getSort);
		List<SysPositionEntity> downEntity = this.list(queryWrapper);
		if (downEntity.size() > 0) {
			//交换两条记录的sort值
			Integer temp = upEntity.getSort();
			upEntity.setSort(downEntity.get(0).getSort());
			downEntity.get(0).setSort(temp);
			this.updateById(downEntity.get(0));
			this.updateById(upEntity);
			isOk = true;
		}
		return isOk;
	}

	@Override
	@Transactional
	public boolean next(String id) {
		boolean isOk = false;
		//获取要下移的那条数据的信息
		SysPositionEntity downEntity = this.getById(id);
		Long upSortCode = downEntity.getSort() == null ? 0L : downEntity.getSort();
		//查询下几条记录
		QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda()
				.gt(SysPositionEntity::getSort, upSortCode)
				.eq(SysPositionEntity::getDeptId, downEntity.getDeptId())
				.orderByAsc(SysPositionEntity::getSort);
		List<SysPositionEntity> upEntity = this.list(queryWrapper);
		if (upEntity.size() > 0) {
			//交换两条记录的sort值
			Integer temp = downEntity.getSort();
			downEntity.setSort(upEntity.get(0).getSort());
			upEntity.get(0).setSort(temp);
			this.updateById(upEntity.get(0));
			this.updateById(downEntity);
			isOk = true;
		}
		return isOk;
	}

	@Override
	public List<SysPositionEntity> getPositionName(List<String> id) {
		List<SysPositionEntity> roleList = new ArrayList<>();
		if (id.size() > 0) {
			QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().in(SysPositionEntity::getId, id);
			roleList = this.list(queryWrapper);
		}
		return roleList;
	}
}