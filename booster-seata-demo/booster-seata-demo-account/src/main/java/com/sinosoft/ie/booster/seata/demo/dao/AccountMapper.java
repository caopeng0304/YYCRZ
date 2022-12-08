package com.sinosoft.ie.booster.seata.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.seata.demo.po.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountMapper extends BaseMapper<Account> {

    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
