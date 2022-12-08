package com.sinosoft.ie.booster.admin.handler;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.entity.SysSocialDetailsEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.mapper.SysSocialDetailsMapper;
import com.sinosoft.ie.booster.admin.service.SysUserService;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.LoginTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @since 2018/11/18
 */
@Slf4j
@Component("WX")
@AllArgsConstructor
public class WeChatLoginHandler extends AbstractLoginHandler {
	private final SysUserService sysUserService;
	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	/**
	 * 微信登录传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 *
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetailsEntity condition = new SysSocialDetailsEntity();
		condition.setType(LoginTypeEnum.WECHAT.getType());
		SysSocialDetailsEntity socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		String url = String.format(SecurityConstants.WX_AUTHORIZATION_CODE_URL
				, socialDetails.getAppId(), socialDetails.getAppSecret(), code);
		String result = HttpUtil.get(url);
		log.debug("微信响应报文:{}", result);

		Object obj = JSONUtil.parseObj(result).get("openid");
		return obj.toString();
	}

	/**
	 * openId 获取用户信息
	 *
	 * @param openId
	 * @return
	 */
	@Override
	public UserInfoModel info(String openId) {
		SysUserEntity user = sysUserService
				.getOne(Wrappers.<SysUserEntity>query()
						.lambda().eq(SysUserEntity::getWxOpenid, openId));

		if (user == null) {
			log.info("微信未绑定:{}", openId);
			return null;
		}
		return sysUserService.getUserInfo(user);
	}
}
