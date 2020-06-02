package com.platform.common.security.props;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.platform.common.security.annotation.Inner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author szhua
 * <p>
 * 资源服务器对外直接暴露URL
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.ignore")
public class PermitAllUrlProperties  implements InitializingBean, ApplicationContextAware {

	private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
	private ApplicationContext applicationContext;

	@Getter
	@Setter
	private List<String> urls = new ArrayList<>();

	@Override
	public void afterPropertiesSet() {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

		map.keySet().forEach(info -> {
			HandlerMethod handlerMethod = map.get(info);

			// 获取方法上边的注解 替代path variable 为 *
			Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
			Optional.ofNullable(method)
					.ifPresent(inner -> info.getPatternsCondition().getPatterns()
							.forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, StringPool.ASTERISK))));

			// 获取类上边的注解, 替代path variable 为 *
			Inner controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
			Optional.ofNullable(controller)
					.ifPresent(inner -> info.getPatternsCondition().getPatterns()
							.forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, StringPool.ASTERISK))));
		});
	}

	@Override
	public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
		this.applicationContext = context;
	}

}
