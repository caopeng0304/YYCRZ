package com.sinosoft.ie.booster.admin.handler;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author lengleng
 * @since 2019/4/8
 * <p>
 * 开源中国登录
 */
@Slf4j
@Component("OSC")
@AllArgsConstructor
public class OscChinaLoginHandler extends AbstractLoginHandler {
	private final SysSocialDetailsMapper sysSocialDetailsMapper;
	private final SysUserService sysUserService;


	/**
	 * 开源中国传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 *
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetailsEntity condition = new SysSocialDetailsEntity();
		condition.setType(LoginTypeEnum.OSC.getType());
		SysSocialDetailsEntity socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		Map<String, Object> params = new HashMap<>(8);

		params.put("client_id", socialDetails.getAppId());
		params.put("client_secret", socialDetails.getAppSecret());
		params.put("grant_type", "authorization_code");
		params.put("redirect_uri", socialDetails.getRedirectUrl());
		params.put("code", code);
		params.put("dataType", "json");

		String result = HttpUtil.post(SecurityConstants.OSC_AUTHORIZATION_CODE_URL, params);
		log.debug("开源中国响应报文:{}", result);

		String accessToken = JSONUtil.parseObj(result).getStr("access_token");

		String url = String.format(SecurityConstants.OSC_USER_INFO_URL, accessToken);
		String resp = HttpUtil.get(url);
		log.debug("开源中国获取个人信息返回报文{}", resp);

		JSONObject userInfo = JSONUtil.parseObj(resp);
		//开源中国唯一标识
		return userInfo.getStr("id");
	}

	/**
	 * identify 获取用户信息
	 *
	 * @param identify 开源中国表示
	 * @return
	 */
	@Override
	public UserInfoModel info(String identify) {


		SysUserEntity user = sysUserService
				.getOne(Wrappers.<SysUserEntity>query()
						.lambda().eq(SysUserEntity::getOscId, identify));

		if (user == null) {
			log.info("开源中国未绑定:{}", identify);
			return null;
		}
		return sysUserService.getUserInfo(user);
	}
}
