package com.sinosoft.ie.booster.admin.controller;

import com.sinosoft.ie.booster.admin.service.MobileService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lengleng
 * @since 2018/11/14
 * <p>
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {
	private final MobileService mobileService;

	@Inner(value = false)
	@GetMapping("/{mobile}")
	public R<Boolean> sendSmsCode(@PathVariable String mobile) {
		return mobileService.sendSmsCode(mobile);
	}
}
