package com.sinosoft.ie.booster.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 客户端异常处理
 * 根据AuthenticationException不同细化异常处理
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstants.UTF8);
		response.setContentType(CommonConstants.CONTENT_TYPE);
		R<String> result = new R<>();
		result.setCode(CommonConstants.FAIL);
		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		if (authException != null) {
			result.setMsg("error");
			result.setData(authException.getMessage());
		}
		if (authException instanceof InsufficientAuthenticationException) {
			response.setStatus(org.springframework.http.HttpStatus.FAILED_DEPENDENCY.value());
			result.setMsg("token expire");
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
