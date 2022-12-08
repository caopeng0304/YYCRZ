package com.sinosoft.ie.booster.mqroute.service;

import org.apache.rocketmq.client.producer.SendResult;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.mqroute.entity.MqMessage;

public interface SendService {
	
	
	public R sendMsg(MqMessage msg);
	

}
