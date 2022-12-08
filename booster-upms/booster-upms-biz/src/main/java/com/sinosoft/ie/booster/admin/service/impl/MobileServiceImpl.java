package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.mapper.SysUserMapper;
import com.sinosoft.ie.booster.admin.service.MobileService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.LoginTypeEnum;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lengleng
 * @since 2018/11/14
 * <p>
 * 手机登录相关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobileServiceImpl implements MobileService {
	private final RedisTemplate redisTemplate;
	private final SysUserMapper userMapper;


	/**
	 * 发送手机验证码
	 * TODO: 调用短信网关发送验证码,测试返回前端
	 *
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public R<Boolean> sendSmsCode(String mobile) {
		List<SysUserEntity> userList = userMapper.selectList(Wrappers
				.<SysUserEntity>query().lambda()
				.eq(SysUserEntity::getPhone, mobile));

		if (CollUtil.isEmpty(userList)) {
			log.info("手机号未注册:{}", mobile);
			return R.ok(Boolean.FALSE, "手机号未注册");
		}

		Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + "@" + mobile);

		if (codeObj != null) {
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return R.ok(Boolean.FALSE, "验证码发送过频繁");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		log.debug("手机号生成验证码成功:{},{}", mobile, code);
		redisTemplate.opsForValue().set(
				CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + "@" + mobile
				, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		return R.ok(Boolean.TRUE, code);
	}
}
