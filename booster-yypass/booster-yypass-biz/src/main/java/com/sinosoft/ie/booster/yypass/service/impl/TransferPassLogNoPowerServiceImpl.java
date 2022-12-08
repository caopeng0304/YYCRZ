package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sinosoft.ie.booster.yypass.entity.TransferPassLogNoPowerEntity;
import com.sinosoft.ie.booster.yypass.mapper.TransferPassLogNoPowerMapper;
import com.sinosoft.ie.booster.yypass.service.TransferPassLogNoPowerService;
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
public class TransferPassLogNoPowerServiceImpl extends ServiceImpl<TransferPassLogNoPowerMapper, TransferPassLogNoPowerEntity>
		implements TransferPassLogNoPowerService {

	private final  TransferPassLogNoPowerMapper transferPassLogNoPowerMapper;


	@Override
	public void create(TransferPassLogNoPowerEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, TransferPassLogNoPowerEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public List<TransferPassLogNoPowerEntity> getList(TransferPassLogNoPowerEntity passLogEntity) {
		QueryWrapper<TransferPassLogNoPowerEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passLogEntity);
		return this.transferPassLogNoPowerMapper.selectList(queryWrapper);
	}

	@Override
	public TransferPassLogNoPowerEntity getInfo(String id) {
		QueryWrapper<TransferPassLogNoPowerEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(TransferPassLogNoPowerEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(TransferPassLogNoPowerEntity entity) {

	}

}