package com.sinosoft.ie.booster.admin.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @since 2018/11/18
 */
@Slf4j
@Component("SMS")
@AllArgsConstructor
public class SmsLoginHandler extends AbstractLoginHandler {
	private final SysUserService sysUserService;


	/**
	 * 验证码登录传入为手机号
	 * 不用不处理
	 *
	 * @param mobile
	 * @return
	 */
	@Override
	public String identify(String mobile) {
		return mobile;
	}

	/**
	 * 通过mobile 获取用户信息
	 *
	 * @param identify
	 * @return
	 */
	@Override
	public UserInfoModel info(String identify) {
		SysUserEntity user = sysUserService
				.getOne(Wrappers.<SysUserEntity>query()
						.lambda().eq(SysUserEntity::getPhone, identify));

		if (user == null) {
			log.info("手机号未注册:{}", identify);
			return null;
		}
		return sysUserService.getUserInfo(user);
	}
}
