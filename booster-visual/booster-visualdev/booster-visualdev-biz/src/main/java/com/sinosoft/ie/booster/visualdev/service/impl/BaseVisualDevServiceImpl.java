package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseVisualDevMapper;
import com.sinosoft.ie.booster.visualdev.model.base.PaginationVisualdev;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import com.sinosoft.ie.booster.visualdev.util.CacheKeyUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.visualdev.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 可视化开发功能表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Service
public class BaseVisualDevServiceImpl extends ServiceImpl<BaseVisualDevMapper, BaseVisualDevEntity> implements BaseVisualDevService {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private CacheKeyUtil cacheKeyUtil;

	@Override
	public List<BaseVisualDevEntity> getList(PaginationVisualdev paginationVisualdev) {
		QueryWrapper<BaseVisualDevEntity> queryWrapper = new QueryWrapper<>();
		if (!StrUtil.isEmpty(paginationVisualdev.getKeyword())) {
			queryWrapper.lambda().like(BaseVisualDevEntity::getFullName, paginationVisualdev.getKeyword());
		}
		queryWrapper.lambda().eq(BaseVisualDevEntity::getType, paginationVisualdev.getType());
		queryWrapper.lambda().orderByDesc(BaseVisualDevEntity::getCreateTime);
		return list(queryWrapper);
	}


	@Override
	public List<BaseVisualDevEntity> getList() {
		QueryWrapper<BaseVisualDevEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByAsc(BaseVisualDevEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public BaseVisualDevEntity getInfo(Long id) {
		QueryWrapper<BaseVisualDevEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDevEntity::getId, id);
		return this.getOne(queryWrapper);
	}


	@Override
	public void create(BaseVisualDevEntity entity) {
		entity.setSort(RandomUtil.parses());
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BaseVisualDevEntity entity) {
		entity.setId(id);
		String redisKey = cacheKeyUtil.getVisiualData() + id;
		if (redisUtil.exists(redisKey)) {
			redisUtil.remove(redisKey);
		}
		return this.updateById(entity);
	}

	@Override
	public void delete(BaseVisualDevEntity entity) {
		if (entity != null) {
			this.removeById(entity.getId());
		}
	}
}
