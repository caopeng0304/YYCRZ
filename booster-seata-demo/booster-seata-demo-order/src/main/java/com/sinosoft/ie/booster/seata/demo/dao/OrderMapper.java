package com.sinosoft.ie.booster.seata.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.seata.demo.po.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    void update(Long id, int status);

    void insertOrder(Order order);
}
