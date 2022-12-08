package com.sinosoft.ie.booster.seata.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.seata.demo.dao.AccountMapper;
import com.sinosoft.ie.booster.seata.demo.po.Account;
import com.sinosoft.ie.booster.seata.demo.service.AccountService;
import com.sinosoft.ie.booster.seata.demo.tcc.AccountTccAction;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountMapper accountMapper;
	@Resource
	private AccountTccAction accountTccAction;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->booster-seata-demo-account-samples中扣减账户余额开始-------<");
        //模拟超时异常，全局事务回滚
//        try {
//            Thread.sleep(30*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountMapper.decrease(userId,money);
        LOGGER.info("------->booster-seata-demo-account-samples中扣减账户余额结束-------<");
    }

	@Override
	@GlobalTransactional
	public void accountTccTest(Long userId, BigDecimal money) throws Exception {
		LOGGER.info("userId:" + userId);
		LOGGER.info("money:" + money);
		LOGGER.info("开始调用");

		accountTccAction.reduceBalance(null, userId, money);

	}


}
