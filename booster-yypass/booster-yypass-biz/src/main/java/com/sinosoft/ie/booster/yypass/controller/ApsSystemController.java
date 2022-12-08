package com.sinosoft.ie.booster.yypass.controller;


import com.sinosoft.ie.booster.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * aps_system
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "demo")
@RequestMapping("/demo")
public class ApsSystemController {





	/**
	 * 请求参数地址栏接入的参数
	 * 信息
	 *
	 * @param name
	 * @return
	 */
	@GetMapping("/getbody/{name}")
	public Object selectParmer(@PathVariable(value = "name") String name) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("name",name);
		map.put("age",2);
		return R.ok(map);
	}



}
