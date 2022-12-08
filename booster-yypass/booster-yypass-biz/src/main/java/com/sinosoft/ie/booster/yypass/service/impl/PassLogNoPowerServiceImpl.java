package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;
import com.sinosoft.ie.booster.yypass.entity.PassLogNoPowerEntity;
import com.sinosoft.ie.booster.yypass.mapper.PassLogNoPowerMapper;
import com.sinosoft.ie.booster.yypass.service.PassLogNoPowerService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PassLogNoPowerServiceImpl extends ServiceImpl<PassLogNoPowerMapper, PassLogNoPowerEntity> implements PassLogNoPowerService {

	private final PassLogNoPowerMapper passLogNoPowerMapper;


	@Override
	public void create(PassLogNoPowerEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, PassLogNoPowerEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public List<PassLogNoPowerEntity> getList(PassLogNoPowerEntity passLogEntity) {
		QueryWrapper<PassLogNoPowerEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passLogEntity);
		queryWrapper.orderByDesc(true,"add_time");
		return this.passLogNoPowerMapper.selectList(queryWrapper);
	}

	@Override
	public PassLogNoPowerEntity getInfo(String id) {
		QueryWrapper<PassLogNoPowerEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassLogNoPowerEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(PassLogNoPowerEntity entity) {

	}

}