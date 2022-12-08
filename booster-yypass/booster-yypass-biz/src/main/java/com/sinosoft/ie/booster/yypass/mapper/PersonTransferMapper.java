package com.sinosoft.ie.booster.yypass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.yypass.entity.PersonTransferEntity;
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
public interface PersonTransferMapper extends BaseMapper<PersonTransferEntity> {

	List<PersonTransferEntity> getPersonTransferByPersonId(@Param("person_id") String person_id);
	//查询交接信息
	List<PersonTransferEntity> getPersonTransfer(@Param("transfer_person_id") String transfer_person_id);
	//查询自己创建的
	List<String> getTransferCPass(@Param("person_id") String person_id);
    // 查询自己审批过的
	List<String> getTransferPassLog(@Param("transfer_person_id") String transfer_person_id);
	// 查询自己审批过的历史数据
	List<String> getHisTransferPassLog(@Param("transfer_person_id") String transfer_person_id);
	// 查询保卫部的审批记录
	List<String> getApproval(@Param("personId") String personId);
	// 查询不能审批的记录
	List<String> getNoPowerList(@Param("userId") String userId,@Param("state") String state);
     // 二级管理员审批的历史记录
	List<String> getRjglyApproval(@Param("personId") String personId);







}
