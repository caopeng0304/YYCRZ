package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDataEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseVisualDataMapper;
import com.sinosoft.ie.booster.visualdev.model.datascreen.PaginationData;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDataService;
import com.sinosoft.ie.booster.visualdev.util.ConfigValueUtil;
import com.sinosoft.ie.booster.visualdev.util.FileUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.visualdev.util.datascreen.VisualImageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 大屏数据
 *
 * @author booster code generator
 * @since 2021-09-16
 */
@Service
public class BaseVisualDataServiceImpl extends ServiceImpl<BaseVisualDataMapper, BaseVisualDataEntity> implements BaseVisualDataService {
	@Autowired
	private ConfigValueUtil configValueUtil;

	@Override
	public List<BaseVisualDataEntity> getList(PaginationData pagination) {
		QueryWrapper<BaseVisualDataEntity> queryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotEmpty(pagination.getCategoryId())) {
			queryWrapper.lambda().eq(BaseVisualDataEntity::getCategoryId, pagination.getCategoryId());
		}
		if (StrUtil.isNotEmpty(pagination.getKeyword())) {
			queryWrapper.lambda().and(
					t -> t.like(BaseVisualDataEntity::getFullName, pagination.getKeyword())
							.or().like(BaseVisualDataEntity::getEncode, pagination.getKeyword())
			);
		}
		//排序
		queryWrapper.lambda().orderByDesc(BaseVisualDataEntity::getCreateTime);
		Page<BaseVisualDataEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
		IPage<BaseVisualDataEntity> iPages = this.page(page, queryWrapper);
		return pagination.setData(iPages.getRecords(), page.getTotal());
	}

	@Override
	public List<BaseVisualDataEntity> getList() {
		QueryWrapper<BaseVisualDataEntity> queryWrapper = new QueryWrapper<>();
		return this.list(queryWrapper);
	}

	@Override
	public BaseVisualDataEntity getInfo(Long id) {
		QueryWrapper<BaseVisualDataEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDataEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void create(BaseVisualDataEntity entity) {
		entity.setSort(RandomUtil.parses());
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BaseVisualDataEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(BaseVisualDataEntity entity) {
		if (entity != null) {
			String fileName = entity.getScreenShot();
			String path = configValueUtil.getBiVisualPath() + File.separator + VisualImageEnum.SCREENSHOT.getMessage() + File.separator + fileName;
			this.removeById(entity.getId());
			FileUtil.deleteFile(path);
		}
	}

	@Override
	public boolean isExistByName(Long id, String name) {
		QueryWrapper<BaseVisualDataEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDataEntity::getFullName, name);
		if (id != null) {
			queryWrapper.lambda().ne(BaseVisualDataEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}
}
