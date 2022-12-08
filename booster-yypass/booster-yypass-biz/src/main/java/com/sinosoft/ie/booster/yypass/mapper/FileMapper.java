package com.sinosoft.ie.booster.yypass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Mapper
public interface FileMapper extends BaseMapper<FileEntity> {

	/**
	 * 根据给定参数，查找相关数据，支持多条件查询
	 * @param apsSystemEntity
	 * @return List<ApsSystemEntity>
	 */
//	List<PassBasicInfoEntity> selectParmer(PassBasicInfoEntity apsSystemEntity) throws Exception ;

	List<FileEntity> getFile(String fileName);


}