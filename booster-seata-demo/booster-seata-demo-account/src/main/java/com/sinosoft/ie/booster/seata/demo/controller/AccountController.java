package com.sinosoft.ie.booster.seata.demo.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.seata.demo.service.AccountService;
import com.sinosoft.ie.booster.seata.demo.tcc.AccountTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @author dpy
 * @version 1.0
 * @date 2022/3/11 11:56
 */
@RequestMapping(value = "/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
	@Resource
	private AccountTccAction accountTccAction;

    /**
     * 扣减账户余额
     */
    @PostMapping("/decrease")
    public R decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return R.ok("扣减账户余额成功！");
    }

	@PostMapping("/reduce")
    public R reduce(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) throws Exception {
		accountTccAction.reduceBalance(null, userId, money);
        return R.ok("扣减账户余额成功！");
    }
}
