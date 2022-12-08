package com.sinosoft.ie.booster.admin.service;

import com.sinosoft.ie.booster.common.core.util.R;

/**
 * @author lengleng
 * @since 2018/11/14
 */
public interface MobileService {
	/**
	 * 发送手机验证码
	 *
	 * @param mobile mobile
	 * @return code
	 */
	R<Boolean> sendSmsCode(String mobile);
}
