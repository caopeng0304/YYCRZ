package com.sinosoft.ie.booster.admin.handler;

import cn.hutool.core.util.URLUtil;
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

/**
 * @author lengleng
 * @since 2019/4/8
 * <p>
 * 码云登录
 */
@Slf4j
@Component("GITEE")
@AllArgsConstructor
public class GiteeLoginHandler extends AbstractLoginHandler {
	private final SysSocialDetailsMapper sysSocialDetailsMapper;
	private final SysUserService sysUserService;


	/**
	 * 码云登录传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 *
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetailsEntity condition = new SysSocialDetailsEntity();
		condition.setType(LoginTypeEnum.GITEE.getType());
		SysSocialDetailsEntity socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		String url = String.format(SecurityConstants.GITEE_AUTHORIZATION_CODE_URL, code
				, socialDetails.getAppId(), URLUtil.encode(socialDetails.getRedirectUrl()), socialDetails.getAppSecret());
		String result = HttpUtil.post(url, new HashMap<>(0));
		log.debug("码云响应报文:{}", result);

		String accessToken = JSONUtil.parseObj(result).getStr("access_token");
		String userUrl = String.format(SecurityConstants.GITEE_USER_INFO_URL, accessToken);
		String resp = HttpUtil.get(userUrl);
		log.debug("码云获取个人信息返回报文{}", resp);

		JSONObject userInfo = JSONUtil.parseObj(resp);
		//码云唯一标识
		return userInfo.getStr("login");
	}

	/**
	 * identify 获取用户信息
	 *
	 * @param identify identify
	 * @return
	 */
	@Override
	public UserInfoModel info(String identify) {


		SysUserEntity user = sysUserService
				.getOne(Wrappers.<SysUserEntity>query()
						.lambda().eq(SysUserEntity::getGiteeLogin, identify));

		if (user == null) {
			log.info("码云未绑定:{}", identify);
			return null;
		}
		return sysUserService.getUserInfo(user);
	}
}
