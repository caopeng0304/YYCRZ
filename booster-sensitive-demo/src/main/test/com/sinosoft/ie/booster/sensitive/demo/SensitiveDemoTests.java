package com.sinosoft.ie.booster.sensitive.demo;


import com.sinosoft.ie.booster.sensitive.demo.entity.User;
import com.sinosoft.ie.booster.sensitive.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest(classes = SensitiveDemoTests.class)
@MapperScan(value = "com.sinosoft.ie.booster.sensitive.demo.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
class SensitiveDemoTests {
	@Resource
	private UserMapper mapper;

	@Test
	void contextLoads() {
		/*User user = new User(101L, "hi china", "123456", "asd@qq.com", "asd", "asd");
		Assertions.assertEquals(1, mapper.insert(user));
		System.err.println("加密内容：" + user);*/
		User user = mapper.selectById(101L);
		System.err.println("查询数据库内容：" + user);
	}
}
