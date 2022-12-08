package com.sinosoft.ie.booster.mqroute.controller;


import javax.validation.Valid;

import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.annotation.Inner;
import com.sinosoft.ie.booster.mqroute.entity.MqMessage;
import com.sinosoft.ie.booster.mqroute.service.SendService;


@RequestMapping("/sendMsg")
@RestController
public class SendController {
	
	@Autowired
	private SendService SendService;
	
	@PostMapping
	public R   sendMsg(@RequestBody @Valid MqMessage msg) {
		
		return 	SendService.sendMsg(msg);
		
	}
	

	
	
	
	
}
