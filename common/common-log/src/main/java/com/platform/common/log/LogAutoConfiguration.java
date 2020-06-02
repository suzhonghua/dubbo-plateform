package com.platform.common.log;

import com.platform.api.upms.service.LogService;
import com.platform.common.log.aspect.SysLogAspect;
import com.platform.common.log.event.SysLogListener;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author szhua
 * 日志自动配置
 */
@EnableAsync
@AllArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    private final LogService logService;

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener(logService);
    }

    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
