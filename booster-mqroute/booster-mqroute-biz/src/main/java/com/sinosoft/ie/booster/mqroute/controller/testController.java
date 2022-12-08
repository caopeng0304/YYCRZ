package com.sinosoft.ie.booster.mqroute.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "消息队列组件")
@RequestMapping("/test-mq")
public class testController {

//	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;

	@Inner
	@PostMapping("/test")
	public String test( String message) {
		System.out.println(message);
		return "访问到了test";
	}
	
	@ResponseBody
	@PostMapping("/test3")
	public String test3(R r) {
		System.out.println(r.toString());
		return "访问到了test3";
	}
	@ResponseBody
	@PostMapping("/test1")
	public String test1() {

		String a = oAuth2RestTemplate.postForObject("http://booster-mqroute-biz/test-mq/test3", R.ok("kkkk"), String.class);
		
		return a;
	}

	@Autowired
	RestTemplate restTemplate;

	@ResponseBody
	@PostMapping("/test2")
	public String test2() {
		System.out.println("接受请求");
		String url = "http://booster-mqroute-biz/test-mq/test";
		
		MultiValueMap<String, Object> map =new  LinkedMultiValueMap<String, Object>();
		map.add("message", "我是测试数据");
		
		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Bearer " + HttpTool.getTokenStr());
		headers.add("from", "Y");
		HttpEntity entity = new HttpEntity<>( map,headers);
	
		
		ResponseEntity<String> a1  = restTemplate.postForEntity(url, entity, String.class);
		
		
		System.out.println(a1.getBody());

		return a1.getBody();
	}
}
