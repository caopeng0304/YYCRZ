package com.sinosoft.ie.booster.seata.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.seata.demo.po.Storage;

public interface StorageService extends IService<Storage> {

    void decrease(Long productId, Integer count);
}
