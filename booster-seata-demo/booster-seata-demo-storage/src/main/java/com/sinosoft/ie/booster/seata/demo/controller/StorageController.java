package com.sinosoft.ie.booster.seata.demo.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.seata.demo.service.StorageService;
import com.sinosoft.ie.booster.seata.demo.tcc.StorageTccAction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author dpy
 * @version 1.0
 * @date 2022/3/10 11:52
 */
@RestController
@RequestMapping(value = "/storage")
public class StorageController {

    @Resource
    private StorageService storageService;
	@Resource
    private StorageTccAction storageTccAction;

	/**
	 * AT模式调用
	 * 扣减库存
	 * @param productId
	 * @param count
	 * @return
	 */
    @PostMapping("/decrease")
    public R decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return R.ok("扣减库存成功！");
    }

	/**
	 *
	 * TCC模式调用扣减库存
	 *
	 * @param productId
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/reduce")
    public R reduce(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) throws Exception {
		storageTccAction.reduceStorage(productId, count);
        return R.ok("扣减库存成功！");
    }
}
