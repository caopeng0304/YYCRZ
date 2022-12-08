package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.yypass.entity.NoPassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.mapper.NoPassBasicInfoMapper;
import com.sinosoft.ie.booster.yypass.mapper.PassBasicInfoMapper;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.NoPassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.service.NoPassBasicInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Service
public class NoPassBasicInfoServiceImpl extends ServiceImpl<NoPassBasicInfoMapper, NoPassBasicInfoEntity> implements NoPassBasicInfoService {

  @Autowired
  private NoPassBasicInfoMapper passBasicInfoMapper;


	@Override
	public List<NoPassBasicInfoEntity> getList(NoPassBasicInfoEntity entity) {
		QueryWrapper<NoPassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(entity);
		return passBasicInfoMapper.selectList(queryWrapper);
	}

	@Override
	public List<NoPassBasicInfoEntity> getTypeList(NoPassBasicInfoPagination apsSystemPagination, String dataType) {
		return null;
	}

	@Override
	public NoPassBasicInfoEntity getInfo(String id) {
		QueryWrapper<NoPassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(NoPassBasicInfoEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(NoPassBasicInfoEntity entity) {
		if (entity!=null){
			this.removeById(entity.getId());
		}
	}

	@Override
	public void create(NoPassBasicInfoEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, NoPassBasicInfoEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public IPage<NoPassBasicInfoEntity> selectParmer(NoPassBasicInfoPagination passBasicInfoPagination) throws Exception {

		QueryWrapper<NoPassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getNames())){
			queryWrapper.lambda().and(t->t.like(NoPassBasicInfoEntity::getNames,passBasicInfoPagination.getNames()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPhone())){
			queryWrapper.lambda().and(t->t.like(NoPassBasicInfoEntity::getPhone,passBasicInfoPagination.getPhone()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getDescs())){
			queryWrapper.lambda().and(t->t.like(NoPassBasicInfoEntity::getDescs,passBasicInfoPagination.getDescs()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getReason())){
			queryWrapper.lambda().and(t->t.like(NoPassBasicInfoEntity::getReason,passBasicInfoPagination.getReason()));
		}

		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");

		Page<NoPassBasicInfoEntity> page=new Page<>(passBasicInfoPagination.getCurrentPage(), passBasicInfoPagination.getPageSize());
		IPage<NoPassBasicInfoEntity> userIPage=this.page(page,queryWrapper);
		return userIPage;
	}
}