package com.sinosoft.ie.booster.common.feign.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * sentinel 请求头解析判断
 * 根据调用来源判断此次请求是否允许放行，可以使用 Sentinel 的来源访问控制功能。
 * 来源访问控制根据资源的请求来源（origin）限制资源是否通过，
 * 若配置白名单则只有请求来源位于白名单内时才可通过；
 * 若配置黑名单则请求来源位于黑名单时不通过，其余的请求通过。
 *
 * @author haocy
 * @since 2021-12-15
 */
public class BoosterHeaderRequestOriginParser implements RequestOriginParser {

	/**
	 * 请求头获取allow
	 */
	private static final String ALLOW = "Allow";

	/**
	 * Parse the origin from given HTTP request.
	 * @param request HTTP request
	 * @return parsed origin
	 */
	@Override
	public String parseOrigin(HttpServletRequest request) {
		return request.getHeader(ALLOW);
	}

}
