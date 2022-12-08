package com.sinosoft.ie.booster.yypass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Mapper
public interface PassBasicInfoMapper extends BaseMapper<PassBasicInfoEntity> {

	/**
	 * 根据给定参数，查找相关数据，支持多条件查询
	 * @param apsSystemEntity
	 * @return List<ApsSystemEntity>
	 */
//	List<PassBasicInfoEntity> selectParmer(PassBasicInfoEntity apsSystemEntity) throws Exception ;

     Long getSNo();

	public List<Map<String,Object>> groupPersonState(PassBasicInfoEntity entity);

	public List<Map<String,Object>> groupPersonDelayState(PassBasicInfoEntity entity);
}
