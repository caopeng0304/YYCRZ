package com.sinosoft.ie.booster.mqroute.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;

/**
 *
 * consumer
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-03-16 15:50:58
 */
@Mapper
public interface ConsumerMapper extends BaseMapper<ConsumerEntity> {
	
	
	List<ConsumerEntity>  selectJoinTopic(Page page, @Param(Constants.WRAPPER) QueryWrapper<ConsumerEntity> queryWrapper);

}
