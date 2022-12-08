package com.sinosoft.ie.booster.common.security.component;

import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sinosoft.ie.booster.common.security.annotation.Inner;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 资源服务器对外直接暴露URL,如果设置context-path要特殊处理
 *
 * @author lengleng
 * @since 2020-03-11
 */
@Slf4j
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class PermitAllUrlProperties implements InitializingBean {

	private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
	private static final String ASTERISK = "*";

	@Getter
	@Setter
	private List<String> urls = new ArrayList<>();

	@Override
	public void afterPropertiesSet() {
		RequestMappingHandlerMapping mapping = SpringUtil.getBean("requestMappingHandlerMapping");
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

		map.keySet().forEach(info -> {
			HandlerMethod handlerMethod = map.get(info);

			// 获取方法上边的注解 替代path variable 为 *
			Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
			addToPermitUrls(info, method);

			// 获取类上边的注解, 替代path variable 为 *
			Inner controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
			addToPermitUrls(info, controller);
		});
	}

	private void addToPermitUrls(RequestMappingInfo info, Inner innerAnnotation) {
		if (innerAnnotation != null) {
			//兼容AntPathPattern和PathPattern模式
			PatternsRequestCondition antPatterns = info.getPatternsCondition();
			Optional.ofNullable(antPatterns).ifPresent(p -> p
					.getPatterns().forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, ASTERISK))));
			PathPatternsRequestCondition pathPatterns = info.getPathPatternsCondition();
			Optional.ofNullable(pathPatterns).ifPresent(p -> p
					.getPatternValues().forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, ASTERISK))));
		}
	}

}
