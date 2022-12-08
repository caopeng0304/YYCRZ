package com.sinosoft.ie.booster.seata.demo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Transaction implements Serializable {
    /**
    * 事务TxId
    */
    private String txId;

    /**
    * 分支事务id
    */
    private String actionId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;

    /**
    * 账户UID
    */
    private String userId;

    /**
    * 变动金额
    */
    private String amount;

    /**
    * 变动类型
    */
    private String type;

    /**
    * 状态: 1初始化; 2已提交; 3已回滚
    */
    private Short state;

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}
}