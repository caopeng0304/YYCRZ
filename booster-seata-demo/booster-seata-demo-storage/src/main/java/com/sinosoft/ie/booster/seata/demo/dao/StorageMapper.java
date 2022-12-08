package com.sinosoft.ie.booster.seata.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.seata.demo.po.Storage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageMapper extends BaseMapper<Storage> {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}
