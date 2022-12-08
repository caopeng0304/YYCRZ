package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sinosoft.ie.booster.yypass.entity.SendMsgLogEntity;
import com.sinosoft.ie.booster.yypass.mapper.SendMsgLogMapper;
import com.sinosoft.ie.booster.yypass.service.SendMsgLogService;
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
public class SendMsgLogServiceImpl extends ServiceImpl<SendMsgLogMapper, SendMsgLogEntity> implements SendMsgLogService {

	private final  SendMsgLogMapper sendMsgLogMapper;


	@Override
	public void create(SendMsgLogEntity entity) {
		this.save(entity);
	}



}