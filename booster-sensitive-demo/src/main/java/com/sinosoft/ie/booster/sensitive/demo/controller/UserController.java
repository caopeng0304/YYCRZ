package com.sinosoft.ie.booster.sensitive.demo.controller;

import com.sinosoft.ie.booster.sensitive.demo.entity.User;
import com.sinosoft.ie.booster.sensitive.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
	@Resource
	private UserMapper mapper;

	@GetMapping("/list")
	public User findUserList() {
		User user = mapper.selectById(101L);
		System.err.println("查询数据库内容：" + user);
		return user;
	}
}
