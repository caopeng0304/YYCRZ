package com.sinosoft.ie.booster.seata.demo;

import com.sinosoft.ie.booster.seata.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
public class AccountTest {

	@Resource
	private AccountService accountService;

	@Test
	void accountTccTest() throws Exception {
		accountService.accountTccTest(Long.valueOf(10000), BigDecimal.valueOf(3000));
	}

}
