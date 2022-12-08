package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sinosoft.ie.booster.yypass.entity.TransferPassLogEntity;
import com.sinosoft.ie.booster.yypass.mapper.TransferPassLogMapper;
import com.sinosoft.ie.booster.yypass.service.TransferPassLogService;
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
public class TransferPassLogServiceImpl extends ServiceImpl<TransferPassLogMapper, TransferPassLogEntity> implements TransferPassLogService {

	private final  TransferPassLogMapper transferPassLogMapper;


	@Override
	public void create(TransferPassLogEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, TransferPassLogEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public List<TransferPassLogEntity> getList(TransferPassLogEntity passLogEntity) {
		QueryWrapper<TransferPassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passLogEntity);
		return this.transferPassLogMapper.selectList(queryWrapper);
	}

	@Override
	public TransferPassLogEntity getInfo(String id) {
		QueryWrapper<TransferPassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(TransferPassLogEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(TransferPassLogEntity entity) {

	}

}