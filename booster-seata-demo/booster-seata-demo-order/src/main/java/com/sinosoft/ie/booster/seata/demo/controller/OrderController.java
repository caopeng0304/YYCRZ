package com.sinosoft.ie.booster.seata.demo.controller;

import com.sinosoft.ie.booster.seata.demo.po.Order;
import com.sinosoft.ie.booster.seata.demo.service.OrderService;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author dpy
 * @version 1.0
 * @date 2022/3/11 11:56
 */
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * AT模式调用
	 * @param order
	 * @return
	 */
	@PostMapping("/create")
	public R create(@RequestBody Order order) {
		orderService.create(order);
		return R.ok("订单创建成功!");
	}

	/**
	 * TCC模式创建订单调用
	 * @param userId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/createOrder")
	public R createOrder(@RequestParam("userId") Long userId,
						 @RequestParam("productId") Long productId) throws Exception {

		orderService.createOrder(userId, productId);

		return R.ok("订单创建成功!");
	}
}
