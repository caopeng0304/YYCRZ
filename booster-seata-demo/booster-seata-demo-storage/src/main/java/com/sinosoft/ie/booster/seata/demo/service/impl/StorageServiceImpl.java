package com.sinosoft.ie.booster.seata.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.seata.demo.dao.StorageMapper;
import com.sinosoft.ie.booster.seata.demo.po.Storage;
import com.sinosoft.ie.booster.seata.demo.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author dpy
 * @version 1.0
 * @date 2022/3/11 11:56
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->booster-seata-demo-storage-samples中扣减库存开始-------<");
        storageMapper.decrease(productId, count);
        LOGGER.info("------->booster-seata-demo-storage-samples中扣减库存结束-------<");
    }
}
