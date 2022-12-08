package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sinosoft.ie.booster.yypass.entity.TransferCPassEntity;
import com.sinosoft.ie.booster.yypass.mapper.TransferCPassMapper;
import com.sinosoft.ie.booster.yypass.service.TransferCPassService;
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
public class TransferCPassServiceImpl extends ServiceImpl<TransferCPassMapper, TransferCPassEntity> implements TransferCPassService {

	private final  TransferCPassMapper transferCPassMapper;


	@Override
	public void create(TransferCPassEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, TransferCPassEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public List<TransferCPassEntity> getList(TransferCPassEntity passLogEntity) {
		QueryWrapper<TransferCPassEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passLogEntity);
		return this.transferCPassMapper.selectList(queryWrapper);
	}

	@Override
	public TransferCPassEntity getInfo(String id) {
		QueryWrapper<TransferCPassEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(TransferCPassEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(TransferCPassEntity entity) {

	}

}