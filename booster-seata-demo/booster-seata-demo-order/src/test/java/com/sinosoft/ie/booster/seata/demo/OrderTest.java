package com.sinosoft.ie.booster.seata.demo;

import com.sinosoft.ie.booster.seata.demo.po.Order;
import com.sinosoft.ie.booster.seata.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class OrderTest {

	@Resource
	private OrderService orderService;

	@Test
    void testSeataAT() {

		Order data = new Order();
		data.setUserId(Long.valueOf(10086));
		data.setMoney(BigDecimal.valueOf(200));
		data.setCount(6);
		data.setProductId(Long.valueOf(1001));

		orderService.create(data);

    }

	@Test
	void testSeataTcc() {
		orderService.createOrder(Long.valueOf(10000), Long.valueOf(1001));
	}

}
