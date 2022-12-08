package com.sinosoft.ie.booster.yypass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Mapper
public interface PassLogMapper extends BaseMapper<PassLogEntity> {

	/**
	 * 根据给定参数，查找相关数据，支持多条件查询
	 * @param
	 * @return List<ApsSystemEntity>
	 */
	List<PassLogEntity> selectParmer(@Param("passBasicInfoId") String passBasicInfoId) ;

	String getRoleCode(@Param("state") String state);

	List<String> getApproval(PassBasicInfoPagination passBasicInfoPagination);

	List<String> getEjdwzgldApproval(PassBasicInfoPagination passBasicInfoPagination);

	List<String> getYrffzrApproval(PassBasicInfoPagination passBasicInfoPagination);

	List<String> getRjglyApproval(PassBasicInfoPagination passBasicInfoPagination);

	List<String> getTopState(@Param("passBasicInfoId") String passBasicInfoId);

	public List<String> getRepeatList(@Param("list") String list);




}
