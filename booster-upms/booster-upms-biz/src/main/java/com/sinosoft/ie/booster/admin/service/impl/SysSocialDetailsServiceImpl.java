package com.sinosoft.ie.booster.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.entity.SysSocialDetailsEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.handler.LoginHandler;
import com.sinosoft.ie.booster.admin.mapper.SysSocialDetailsMapper;
import com.sinosoft.ie.booster.admin.mapper.SysUserMapper;
import com.sinosoft.ie.booster.admin.service.SysSocialDetailsService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.LoginTypeEnum;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author lengleng
 * @since 2018年08月16日
 */
@Slf4j
@AllArgsConstructor
@Service("sysSocialDetailsService")
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetailsEntity> implements SysSocialDetailsService {
	private final Map<String, LoginHandler> loginHandlerMap;
	private final CacheManager cacheManager;
	private final SysUserMapper sysUserMapper;

	/**
	 * 绑定社交账号
	 *
	 * @param type type
	 * @param code code
	 * @return
	 */
	@Override
	public Boolean bindSocial(String type, String code) {
		String identify = loginHandlerMap.get(type).identify(code);
		SysUserEntity sysUser = sysUserMapper.selectById(SecurityUtils.getUser().getId());
		if (LoginTypeEnum.GITEE.getType().equals(type)) {
			sysUser.setGiteeLogin(identify);
		} else if (LoginTypeEnum.OSC.getType().equals(type)) {
			sysUser.setOscId(identify);
		} else if (LoginTypeEnum.WECHAT.getType().equals(type)) {
			sysUser.setWxOpenid(identify);
		} else if (LoginTypeEnum.QQ.getType().equals(type)) {
			sysUser.setQqOpenid(identify);
		}
		sysUserMapper.updateById(sysUser);
		//更新緩存
		Objects.requireNonNull(cacheManager.getCache(CacheConstants.USER_DETAILS)).evict(sysUser.getUsername());
		return Boolean.TRUE;
	}

	/**
	 * 根据入参查询用户信息
	 *
	 * @param inStr TYPE@code
	 * @return
	 */
	@Override
	public UserInfoModel getUserInfo(String inStr) {
		String[] inStrs = inStr.split(StringPool.AT);
		String type = inStrs[0];
		String loginStr = inStrs[1];
		return loginHandlerMap.get(type).handle(loginStr);
	}
}
