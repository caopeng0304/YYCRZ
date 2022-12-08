package com.sinosoft.ie.booster.seata.demo.feign;

import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 库存Feign接口
 *
 * @author dpy
 * @throws
 * @date 2022/3/11 11:44
 */
@FeignClient(value = "booster-seata-demo-storage")
public interface StorageFeignClient {

	/**
	 * @param productId 产品id
	 * @return R
	 * @throws
	 * @param: count 数量
	 */
	@PostMapping("/storage/decrease")
	R decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

	/**
	 * TCC调用
	 *
	 * @param productId
	 * @param count
	 * @return R
	 */
	@PostMapping("/storage/reduce")
	R reduce(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
