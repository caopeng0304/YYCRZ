package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.entity.BasePortalEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BasePortalMapper;
import com.sinosoft.ie.booster.visualdev.model.portal.PortalPagination;
import com.sinosoft.ie.booster.visualdev.service.BasePortalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门户表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Service
public class BasePortalServiceImpl extends ServiceImpl<BasePortalMapper, BasePortalEntity> implements BasePortalService {
	@Override
	public List<BasePortalEntity> getList(PortalPagination portalPagination) {
		QueryWrapper<BasePortalEntity> queryWrapper = new QueryWrapper<>();
		if (!StrUtil.isEmpty(portalPagination.getKeyword())) {
			queryWrapper.lambda().like(BasePortalEntity::getFullName, portalPagination.getKeyword());
		}
		queryWrapper.lambda().orderByDesc(BasePortalEntity::getCreateTime);
		return list(queryWrapper);
	}


	@Override
	public List<BasePortalEntity> getList() {
		QueryWrapper<BasePortalEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByAsc(BasePortalEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public BasePortalEntity getInfo(Long id) {
		QueryWrapper<BasePortalEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BasePortalEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void create(BasePortalEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BasePortalEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(BasePortalEntity entity) {
		if (entity != null) {
			this.removeById(entity.getId());
		}
	}
}
