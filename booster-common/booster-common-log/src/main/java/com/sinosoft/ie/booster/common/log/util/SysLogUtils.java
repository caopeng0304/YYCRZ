package com.sinosoft.ie.booster.common.log.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

/**
 * 系统日志工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SysLogUtils {

	public SysLogEntity getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLogEntity sysLog = new SysLogEntity();
		sysLog.setCreateBy(getUsername());
		sysLog.setUpdateBy(getUsername());
		sysLog.setType(LogTypeEnum.NORMAL.getType());
		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(getClientId(request));
		return sysLog;
	}

	/**
	 * 获取客户端
	 * @return clientId
	 */
	private String getClientId(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			return auth2Authentication.getOAuth2Request().getClientId();
		}
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			// 通过请求参数拿到clientId
			String authorizationHeaderValue = request.getHeader("Authorization");
			String base64AuthorizationHeader = Optional.ofNullable(authorizationHeaderValue)
					.map(headerValue -> headerValue.substring("Basic ".length())).orElse("");
			if (StrUtil.isNotEmpty(base64AuthorizationHeader)) {
				String decodedAuthorizationHeader = new String(Base64.getDecoder().decode(base64AuthorizationHeader),
						StandardCharsets.UTF_8);
				return decodedAuthorizationHeader.split(":")[0];
			}
		}
		return null;
	}

	/**
	 * 获取用户名称
	 * @return username
	 */
	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getName();
	}

}
