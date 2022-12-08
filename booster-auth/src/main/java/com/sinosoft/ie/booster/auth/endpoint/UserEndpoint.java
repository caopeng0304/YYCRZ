package com.sinosoft.ie.booster.auth.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 用户端点
 *
 * @author haocy
 * @since 2022/02/15
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEndpoint {

	/**
	 * 获取当前用户信息
	 *
	 * @param principal
	 * @return
	 */
	@GetMapping("/getUserInfo")
	public Principal getUserInfo(Principal principal) {
		return principal;
	}

}
