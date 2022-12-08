package com.sinosoft.ie.booster.seata.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.seata.demo.po.Account;

import java.math.BigDecimal;


public interface AccountService extends IService<Account> {
    void decrease(Long userId, BigDecimal money);

    void accountTccTest(Long userId, BigDecimal money) throws Exception;
}
