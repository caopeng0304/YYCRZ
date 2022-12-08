package com.sinosoft.ie.booster.common.risk.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.risk.RiskProperties;
import com.sinosoft.ie.booster.common.risk.model.EventRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * The servlet filter for risk inspection
 *
 * @author haocy
 * @since 2022-11-24
 */
@RequiredArgsConstructor
public class RiskFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RiskFilter.class);

	private final RestTemplate restTemplate;

	private final RiskProperties riskProperties;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestUrl = URLUtil.getPath(httpRequest.getRequestURI());
		if (requestUrl.startsWith("/actuator/prometheus")) {
			chain.doFilter(request, response);
			return;
		}
		String reqId = IdUtil.getSnowflakeNextIdStr();
		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("logId", reqId);
		jsonInfo.put("userId", Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(Principal::getName).orElse(null));
		jsonInfo.put("logTime", System.currentTimeMillis());
		jsonInfo.put("userIP", ServletUtil.getClientIP(httpRequest));
		jsonInfo.put("requestUrl", requestUrl);
		jsonInfo.put("params", HttpUtil.toParams(httpRequest.getParameterMap()));
		jsonInfo.put("deviceId", httpRequest.getHeader(HttpHeaders.USER_AGENT));
		EventRequest eventRequest = new EventRequest(riskProperties.getModelGuid(), reqId, jsonInfo);
		HttpEntity<EventRequest> entity = new HttpEntity<>(eventRequest);
		long startTime = System.currentTimeMillis();
		ResponseEntity<JSONObject> resp = restTemplate.exchange(riskProperties.getUploadUrl(), HttpMethod.POST, entity, JSONObject.class);
		long totalTime = System.currentTimeMillis() - startTime;
		logger.info("risk result: total time {}ms,status={},body={}", totalTime, resp.getStatusCode(), resp.getBody());
		if (resp.getStatusCode() == HttpStatus.OK) {
			try {
				JSONObject result = resp.getBody();
				assert result != null;
				JSONObject activations = result.getJSONObject("activations");
				for (Map.Entry<String, Object> entry : activations.entrySet()) {
					Map<String, Object> riskStatus = JsonUtil.entityToMap(entry.getValue());
					if (!"pass".equals(riskStatus.get("risk"))) {
						HttpServletResponse httpResponse = (HttpServletResponse) response;
						httpResponse.addHeader("Content-Type", "application/json;charset=UTF-8");
						ServletOutputStream outputStream = httpResponse.getOutputStream();
						outputStream.write(JSON.toJSONBytes(R.failed(riskStatus, "风控引擎检测到您访问过于频繁，请稍后再试！")));
						outputStream.flush();
						return;
					}
				}
			} catch (Exception e) {
				logger.info("risk error", e);
			}
		}
		chain.doFilter(request, response);
	}
}
