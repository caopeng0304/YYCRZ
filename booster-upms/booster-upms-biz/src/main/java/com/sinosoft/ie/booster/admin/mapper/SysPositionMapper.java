package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_position
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-09-14 15:10:15
 */
@Mapper
public interface SysPositionMapper extends BaseMapper<SysPositionEntity> {

	List<SysPositionEntity> getListByUserName(@Param("userName") String userName);

	List<String> getUserPositionByIds(@Param("sql") String sql);
}
