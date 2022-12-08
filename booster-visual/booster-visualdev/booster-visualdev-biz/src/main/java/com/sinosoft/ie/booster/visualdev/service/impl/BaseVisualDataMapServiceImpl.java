package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDataMapEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseVisualDataMapMapper;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDataMapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大屏地图
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Service
public class BaseVisualDataMapServiceImpl extends ServiceImpl<BaseVisualDataMapMapper, BaseVisualDataMapEntity> implements BaseVisualDataMapService {

	@Override
	public List<BaseVisualDataMapEntity> getList(Pagination pagination) {
		QueryWrapper<BaseVisualDataMapEntity> queryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotEmpty(pagination.getKeyword())) {
			queryWrapper.lambda().and(
					t -> t.like(BaseVisualDataMapEntity::getFullName, pagination.getKeyword())
							.or().like(BaseVisualDataMapEntity::getEncode, pagination.getKeyword())
			);
		}
		//排序
		queryWrapper.lambda().orderByAsc(BaseVisualDataMapEntity::getSort);
		Page<BaseVisualDataMapEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
		IPage<BaseVisualDataMapEntity> IPages = this.page(page, queryWrapper);
		return pagination.setData(IPages.getRecords(), page.getTotal());
	}

	@Override
	public List<BaseVisualDataMapEntity> getList() {
		QueryWrapper<BaseVisualDataMapEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByDesc(BaseVisualDataMapEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public BaseVisualDataMapEntity getInfo(Long id) {
		QueryWrapper<BaseVisualDataMapEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDataMapEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void create(BaseVisualDataMapEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BaseVisualDataMapEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(BaseVisualDataMapEntity entity) {
		if (entity != null) {
			this.removeById(entity.getId());
		}
	}

	@Override
	public boolean isExistByFullName(String fullName, Long id) {
		QueryWrapper<BaseVisualDataMapEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDataMapEntity::getFullName, fullName);
		if (id != null) {
			queryWrapper.lambda().ne(BaseVisualDataMapEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public boolean isExistByEnCode(String enCode, Long id) {
		QueryWrapper<BaseVisualDataMapEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDataMapEntity::getEncode, enCode);
		if (id != null) {
			queryWrapper.lambda().ne(BaseVisualDataMapEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}
}
