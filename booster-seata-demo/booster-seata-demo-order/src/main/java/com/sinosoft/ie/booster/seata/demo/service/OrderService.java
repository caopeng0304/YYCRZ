package com.sinosoft.ie.booster.seata.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.seata.demo.po.Order;

public interface OrderService extends IService<Order> {

    void create(Order order);

	void createOrder(Long userId, Long productId);
}
