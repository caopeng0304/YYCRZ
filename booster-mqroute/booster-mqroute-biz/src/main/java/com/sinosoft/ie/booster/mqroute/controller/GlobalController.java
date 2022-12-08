package com.sinosoft.ie.booster.mqroute.controller;

import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.feign.handle.GlobalBizExceptionHandler;
import com.sinosoft.ie.booster.mqroute.err.MqError;

@ControllerAdvice
public class GlobalController  extends GlobalBizExceptionHandler{
	
	@ResponseBody
	@ExceptionHandler(value = MqError.class)
	public R validateErrorHandler( MqError e)  {
		
		return R.failed(e.getMsg());
	}
	
	@ResponseBody
	@ExceptionHandler(value = MessagingException.class)
	public R validateErrorHandler( MessagingException e)  {
		
		return R.failed("消息发送失败");
	}
	
	
	

}
