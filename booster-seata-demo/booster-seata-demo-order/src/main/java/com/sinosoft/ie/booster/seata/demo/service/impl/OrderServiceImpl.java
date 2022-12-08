package com.sinosoft.ie.booster.seata.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.seata.demo.dao.OrderMapper;
import com.sinosoft.ie.booster.seata.demo.feign.AccountFeignClient;
import com.sinosoft.ie.booster.seata.demo.feign.StorageFeignClient;
import com.sinosoft.ie.booster.seata.demo.po.Order;
import com.sinosoft.ie.booster.seata.demo.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @author dpy
 * @version 1.0
 * @date 2022/3/11 11:56
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private AccountFeignClient accountFeignClient;

    @Resource
    private StorageFeignClient storageFeignClient;

    @Resource
    private OrderMapper orderMapper;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    @Override
    //开启分布式事务
    @GlobalTransactional
    public void create(Order order) {
        LOGGER.info("------->下单开始");

		// 定义参数
		Long productId = order.getProductId();
		Integer count = order.getCount();
		Long userId = order.getUserId();
		BigDecimal money = order.getMoney();

        //远程调用库存服务扣减库存
        LOGGER.info("------->seata-order-samples中扣减库存开始-------<");
        storageFeignClient.decrease(productId, count);
        LOGGER.info("------->seata-order-samples中扣减库存结束-------<");

        //远程调用账户服务扣减余额
        LOGGER.info("------->seata-order-samples中扣减余额开始-------<");
        accountFeignClient.decrease(userId, money);
        LOGGER.info("------->seata-order-samples中扣减余额结束-------<");

		//本应用创建订单
		orderMapper.insertOrder(order);

        LOGGER.info("------->下单结束");
    }

	@Override
	@GlobalTransactional
	public void createOrder(Long userId, Long productId) {
		LOGGER.info("------->下单开始<--------");
		LOGGER.info("当前 XID: {}", RootContext.getXID());

		// 初始化商品数量
		Integer count = 2;
		BigDecimal money = BigDecimal.valueOf(300);

		// 减少库存
		storageFeignClient.reduce(productId, count);

		// 减少余额
		accountFeignClient.reduce(userId, money);

		// 下订单
		Order order = new Order();
		order.setUserId(userId);
		order.setProductId(productId);
		order.setCount(count);
		order.setMoney(money);
		order.setStatus(1);

		// 插入订单
		orderMapper.insertOrder(order);

		LOGGER.info("------->下单成功<--------");
	}
}
