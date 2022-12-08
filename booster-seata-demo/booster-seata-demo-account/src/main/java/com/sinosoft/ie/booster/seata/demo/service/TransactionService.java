/**
* @mbg.generated
* generator on Tue Mar 22 14:34:30 GMT+08:00 2022
*/
package com.sinosoft.ie.booster.seata.demo.service;

import com.sinosoft.ie.booster.seata.demo.po.Transaction;

public interface TransactionService {
    /**
    * deleteByPrimaryKey
    * @param txId txId
    * @param actionId actionId
    * @return int int
    */
    int deleteByPrimaryKey(String txId, String actionId);

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
    Transaction selectByPrimaryKey(String txId, String actionId);

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