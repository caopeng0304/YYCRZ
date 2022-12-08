package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sinosoft.ie.booster.yypass.entity.PersonTransferEntity;
import com.sinosoft.ie.booster.yypass.mapper.PersonTransferMapper;
import com.sinosoft.ie.booster.yypass.service.PersonTransferService;
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
public class PersonTransferServiceImpl extends ServiceImpl<PersonTransferMapper, PersonTransferEntity> implements PersonTransferService {

	private final  PersonTransferMapper personTransferMapper;


	@Override
	public void create(PersonTransferEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, PersonTransferEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public List<PersonTransferEntity> getList(PersonTransferEntity passLogEntity) {
		QueryWrapper<PersonTransferEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passLogEntity);
		return this.personTransferMapper.selectList(queryWrapper);
	}

	@Override
	public PersonTransferEntity getInfo(String id) {
		QueryWrapper<PersonTransferEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PersonTransferEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(PersonTransferEntity entity) {

	}

	public List<PersonTransferEntity> getPersonTransfer(String transfer_person_id){
            return personTransferMapper.getPersonTransfer(transfer_person_id);
	}

}