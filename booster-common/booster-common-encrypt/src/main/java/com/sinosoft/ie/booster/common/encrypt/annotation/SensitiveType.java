package com.sinosoft.ie.booster.common.encrypt.annotation;

/**
 * @author 段鹏宇
 */

public enum SensitiveType {

	/**
	 * 用户名
	 */
	ChineseName,

	/**
	 * 身份证号
	 */
	IdCard,

	/**
	 * 手机号
	 */
	Phone,

	/**
	 * 电话号
	 */
	Mobile,

	/**
	 * 地址
	 */
	Address,

	/**
	 * 邮件
	 */
	Email,

    /**
     * 银行卡
	 */
	BankCard,

    /**
     * 密码
	 */
	Password,

    /**
     * 车牌
	 */
	CarNumber,

	/**
     * 自定义脱敏
	 */
	Customize;

}
