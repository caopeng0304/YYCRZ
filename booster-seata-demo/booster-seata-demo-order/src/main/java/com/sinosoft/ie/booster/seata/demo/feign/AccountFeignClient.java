package com.sinosoft.ie.booster.seata.demo.feign;

import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 用户模块Feign接口
 *
 * @author dpy
 * @date 2022/3/11 11:44
 */
@FeignClient(value = "booster-seata-demo-account")
public interface AccountFeignClient {

    /**
	 * AT
     * 扣减账户余额
     *
     * @param userId 用户ID
     * @return R
     * @param: money 钱
     */
    @PostMapping("/account/decrease")
	R decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

	/**
	 * TCC模式
	 * 扣减账户余额
	 *
	 * @param userId
	 * @param money
	 * @return R
	 */
	@PostMapping("/account/reduce")
	R reduce(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
