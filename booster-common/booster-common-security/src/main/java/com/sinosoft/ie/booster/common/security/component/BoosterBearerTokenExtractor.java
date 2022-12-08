package com.sinosoft.ie.booster.common.security.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * 改造 {@link BearerTokenExtractor} 对公开权限的请求不进行校验
 *
 * @author caiqy
 * @since 2020.05.15
 */
public class BoosterBearerTokenExtractor extends BearerTokenExtractor {

	private final PathMatcher pathMatcher;

	private final PermitAllUrlProperties urlProperties;

	public BoosterBearerTokenExtractor(PermitAllUrlProperties urlProperties) {
		this.urlProperties = urlProperties;
		this.pathMatcher = new AntPathMatcher();
	}

	@Override
	public Authentication extract(HttpServletRequest request) {
		boolean match = urlProperties.getUrls().stream()
				.anyMatch(url -> pathMatcher.match(url, request.getRequestURI()));

		return match ? null : super.extract(request);
	}

}
