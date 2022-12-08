package com.sinosoft.ie.booster.visualdev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.entity.BaseCommonFieldsEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseCommonFieldsMapper;
import com.sinosoft.ie.booster.visualdev.service.BaseCommonFieldsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 常用表字段
 *
 * @author booster code generator
 * @since 2021-09-17
 */
@Service
public class BaseCommonFieldsServiceImpl extends ServiceImpl<BaseCommonFieldsMapper, BaseCommonFieldsEntity> implements BaseCommonFieldsService {

	@Override
	public List<BaseCommonFieldsEntity> getList() {
		QueryWrapper<BaseCommonFieldsEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByAsc(BaseCommonFieldsEntity::getSort);
		return this.list(queryWrapper);
	}

	@Override
	public BaseCommonFieldsEntity getInfo(Long id) {
		QueryWrapper<BaseCommonFieldsEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseCommonFieldsEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public boolean isExistByFullName(String fullName, Long id) {
		QueryWrapper<BaseCommonFieldsEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseCommonFieldsEntity::getFieldName, fullName);
		if (id != null) {
			queryWrapper.lambda().ne(BaseCommonFieldsEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}


	@Override
	public void create(BaseCommonFieldsEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BaseCommonFieldsEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(BaseCommonFieldsEntity entity) {
		if (entity != null) {
			this.removeById(entity.getId());
		}
	}
}
