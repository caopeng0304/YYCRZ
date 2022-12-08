package com.sinosoft.ie.booster.seata.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.seata.demo.po.Account;
import com.sinosoft.ie.booster.seata.demo.po.Transaction;
import org.apache.ibatis.annotations.Param;

public interface TransactionMapper extends BaseMapper<Account> {
    /**
    * deleteByPrimaryKey
    * @param txId txId
    * @param actionId actionId
    * @return int int
    */
    int deleteByPrimaryKey(@Param("txId") String txId, @Param("actionId") String actionId);

    /**
    * insert
    * @param row row
    * @return int int
    */
    int insert(Transaction row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(Transaction row);

    /**
    * selectByPrimaryKey
    * @param txId txId
    * @param actionId actionId
    * @return Transaction Transaction
    */
    Transaction selectByPrimaryKey(@Param("txId") String txId, @Param("actionId") String actionId);

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(Transaction row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(Transaction row);
}