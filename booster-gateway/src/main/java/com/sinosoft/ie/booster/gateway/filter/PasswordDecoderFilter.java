package com.sinosoft.ie.booster.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.gateway.config.GatewayConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author lengleng
 * @since 2019 /2/1 密码解密工具类
 */
@Slf4j
@RequiredArgsConstructor
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory<Object> {

	private static final String PASSWORD = "password";

	private static final String KEY_ALGORITHM = "AES";

	private final GatewayConfigProperties configProperties;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();

			// 不是登录请求，直接向下执行
			if (!CharSequenceUtil.containsAnyIgnoreCase(request.getURI().getPath(),
					SecurityConstants.OAUTH_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			URI uri = exchange.getRequest().getURI();
			String queryParam = uri.getRawQuery();
			Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.CHARSET_UTF_8);

			String password = paramMap.get(PASSWORD);
			if (CharSequenceUtil.isNotBlank(password)) {
				try {
					password = decrypt(password, configProperties.getEncodeKey());
				}
				catch (Exception e) {
					log.error("密码解密失败:{}", password);
					return Mono.error(e);
				}
				paramMap.put(PASSWORD, password.trim());
			}

			URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(HttpUtil.toParams(paramMap)).build(true)
					.toUri();

			ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
			return chain.filter(exchange.mutate().request(newRequest).build());
		};
	}

	private static String decrypt(String data, String pass) {
		AES aes = new AES(Mode.CBC, Padding.NoPadding, new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
				new IvParameterSpec(pass.getBytes()));
		byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
		return new String(result, StandardCharsets.UTF_8);
	}

}
