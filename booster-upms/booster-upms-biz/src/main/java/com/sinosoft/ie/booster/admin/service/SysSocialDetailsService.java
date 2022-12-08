package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.entity.SysSocialDetailsEntity;

/**
 * 系统社交登录账号表
 *
 * @author lengleng
 * @since 2018-08-16 21:30:41
 */
public interface SysSocialDetailsService extends IService<SysSocialDetailsEntity> {

	/**
	 * 绑定社交账号
	 *
	 * @param state 类型
	 * @param code  code
	 * @return
	 */
	Boolean bindSocial(String state, String code);

	/**
	 * 根据入参查询用户信息
	 *
	 * @param inStr
	 * @return
	 */
	UserInfoModel getUserInfo(String inStr);
}

