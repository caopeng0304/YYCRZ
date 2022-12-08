package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.yypass.entity.PassDelayEntity;
import com.sinosoft.ie.booster.yypass.mapper.PassDelayMapper;
import com.sinosoft.ie.booster.yypass.service.PassDelayService;
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
public class PassDelayServiceImpl extends ServiceImpl<PassDelayMapper, PassDelayEntity> implements PassDelayService {

  @Autowired
  private PassDelayMapper passDelayMapper;

	@Override
	public List<PassDelayEntity> getList(PassDelayEntity passDelayEntity) {
		QueryWrapper<PassDelayEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passDelayEntity);
		return this.passDelayMapper.selectList(queryWrapper);
	}

	@Override
	public PassDelayEntity getInfo(String id) {
		QueryWrapper<PassDelayEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassDelayEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(PassDelayEntity entity) {
		if (entity!=null){
			this.removeById(entity.getId());
		}
	}

	@Override
	public void create(PassDelayEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, PassDelayEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}
}