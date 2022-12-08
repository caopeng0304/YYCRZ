/**
* @mbg.generated
* generator on Tue Mar 22 14:34:30 GMT+08:00 2022
*/
package com.sinosoft.ie.booster.seata.demo.service.impl;

import com.sinosoft.ie.booster.seata.demo.dao.TransactionMapper;
import com.sinosoft.ie.booster.seata.demo.po.Transaction;
import com.sinosoft.ie.booster.seata.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Resource
	private TransactionMapper transactionMapper;

    /**
    * deleteByPrimaryKey
    * @param txId txId
    * @param actionId actionId
    * @return int int
    */
    @Override
    public int deleteByPrimaryKey(String txId, String actionId) {
        return transactionMapper.deleteByPrimaryKey(txId, actionId);
    }

    /**
    * insert
    * @param row row
    * @return int int
    */
    @Override
    public int insert(Transaction row) {
        return transactionMapper.insert(row);
    }

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    @Override
    public int insertSelective(Transaction row) {
        return transactionMapper.insertSelective(row);
    }

    /**
    * selectByPrimaryKey
    * @param txId txId
    * @param actionId actionId
    * @return Transaction Transaction
    */
    @Override
    public Transaction selectByPrimaryKey(String txId, String actionId) {
        return transactionMapper.selectByPrimaryKey(txId, actionId);
    }

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    @Override
    public int updateByPrimaryKeySelective(Transaction row) {
        return transactionMapper.updateByPrimaryKeySelective(row);
    }

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    @Override
    public int updateByPrimaryKey(Transaction row) {
        return transactionMapper.updateByPrimaryKey(row);
    }
}