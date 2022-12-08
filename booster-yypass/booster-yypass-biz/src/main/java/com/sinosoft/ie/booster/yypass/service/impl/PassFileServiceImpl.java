package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.mapper.PassFileMapper;
import com.sinosoft.ie.booster.yypass.service.PassFileService;
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
public class PassFileServiceImpl extends ServiceImpl<PassFileMapper, PassFileEntity> implements PassFileService {

  @Autowired
  private PassFileMapper passFileMapper;

	@Override
	public List<FileEntity> getFileList(PassFileEntity passFileEntity) {
		return this.passFileMapper.getFileList(passFileEntity);
	}

	@Override
	public List<PassFileEntity> getList(PassFileEntity passFileEntity) {
		QueryWrapper<PassFileEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(passFileEntity);
		return this.passFileMapper.selectList(queryWrapper);
	}

	@Override
	public PassFileEntity getInfo(String id) {
		QueryWrapper<PassFileEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassFileEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(PassFileEntity entity) {
		this.delete(entity);
	}

	@Override
	public void create(PassFileEntity entity) {
           this.save(entity);
	}

	@Override
	public boolean update(String id, PassFileEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	public void logicDelete(String passBasicInfoId,String fileType){
		this.passFileMapper.logicDelete(passBasicInfoId,fileType);
	}


}