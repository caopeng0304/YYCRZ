package com.sinosoft.ie.booster.gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.LoginTypeEnum;
import com.sinosoft.ie.booster.common.core.exception.ValidateException;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.WebUtils;
import com.sinosoft.ie.booster.gateway.config.GatewayConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * The type Validate code gateway filter.
 *
 * @author lengleng
 * @since 2018 /7/4 验证码处理
 */
@Slf4j
@RequiredArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory<Object> {

	private final GatewayConfigProperties configProperties;

	private final ObjectMapper objectMapper;

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			boolean isAuthToken = CharSequenceUtil.containsAnyIgnoreCase(request.getURI().getPath(),
					SecurityConstants.OAUTH_TOKEN_URL, SecurityConstants.SMS_TOKEN_URL
					, SecurityConstants.SOCIAL_TOKEN_URL);

			// 不是登录请求，直接向下执行
			if (!isAuthToken) {
				return chain.filter(exchange);
			}

			// 刷新token，直接向下执行
			String grantType = request.getQueryParams().getFirst("grant_type");
			if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
				return chain.filter(exchange);
			}

			boolean isIgnoreClient = configProperties.getIgnoreClients().contains(WebUtils.getClientId(request));
			try {
				// only oauth and the request not in ignore clients need check code.
				if (!isIgnoreClient) {
					// 如果是社交登录，判断是否包含SMS
					if (StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.SOCIAL_TOKEN_URL)) {
						String mobile = request.getQueryParams().getFirst("mobile");
						if (StrUtil.containsAny(mobile, LoginTypeEnum.SMS.getType())) {
							throw new ValidateException("验证码不合法");
						} else {
							return chain.filter(exchange);
						}
					}

					checkCode(request);
				}
			}
			catch (Exception e) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

				final String errMsg = e.getMessage();
				return response.writeWith(Mono.create(monoSink -> {
					try {
						byte[] bytes = objectMapper.writeValueAsBytes(R.failed(errMsg));
						DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);

						monoSink.success(dataBuffer);
					}
					catch (JsonProcessingException jsonProcessingException) {
						log.error("对象输出异常", jsonProcessingException);
						monoSink.error(jsonProcessingException);
					}
				}));
			}

			return chain.filter(exchange);
		};
	}

	@SneakyThrows
	private void checkCode(ServerHttpRequest request) {
		String code = request.getQueryParams().getFirst("code");

		if (CharSequenceUtil.isBlank(code)) {
			throw new ValidateException("验证码不能为空");
		}

		String randomStr = request.getQueryParams().getFirst("randomStr");
		if (CharSequenceUtil.isBlank(randomStr)) {
			randomStr = request.getQueryParams().getFirst("mobile");
		}

		String key = CacheConstants.DEFAULT_CODE_KEY + randomStr;
		if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
			throw new ValidateException("验证码不合法");
		}

		Object codeObj = redisTemplate.opsForValue().get(key);

		if (codeObj == null) {
			throw new ValidateException("验证码不合法");
		}

		String saveCode = codeObj.toString();
		if (CharSequenceUtil.isBlank(saveCode)) {
			redisTemplate.delete(key);
			throw new ValidateException("验证码不合法");
		}

		if (!StrUtil.equals(saveCode, code)) {
			redisTemplate.delete(key);
			throw new ValidateException("验证码不合法");
		}

		redisTemplate.delete(key);
	}

}
