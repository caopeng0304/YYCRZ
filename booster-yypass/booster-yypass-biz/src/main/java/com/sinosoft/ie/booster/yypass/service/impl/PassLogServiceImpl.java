package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;
import com.sinosoft.ie.booster.yypass.mapper.PassLogMapper;
import com.sinosoft.ie.booster.yypass.service.PassLogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
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
public class PassLogServiceImpl extends ServiceImpl<PassLogMapper, PassLogEntity> implements PassLogService {

	private final  PassLogMapper passLogMapper;


	@Override
	public void create(PassLogEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, PassLogEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public List<PassLogEntity> getList(PassLogEntity passLogEntity) {
		QueryWrapper<PassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passLogEntity);
		queryWrapper.orderByDesc(true,"add_time");
		return this.passLogMapper.selectList(queryWrapper);
	}

	@Override
	public PassLogEntity getInfo(String id) {
		QueryWrapper<PassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassLogEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(PassLogEntity entity) {

	}

	@Override
	public List<PassLogEntity> selectParmer(String passBasicInfoId) {
		return this.passLogMapper.selectParmer(passBasicInfoId);
	}

	public String getRoleCode(String state){
		return this.passLogMapper.getRoleCode(state);
	}

	public List<String> getTopState(String passBasicInfoId){
		return this.passLogMapper.getTopState(passBasicInfoId);
	}

	public List<String> getRepeatList(String list){
		return this.passLogMapper.getRepeatList(list);
	}
}