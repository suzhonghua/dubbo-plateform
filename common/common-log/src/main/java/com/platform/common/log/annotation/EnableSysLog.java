package com.platform.common.log.annotation;

import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.*;

/**
 * @author szhua
 * <p>
 * 日志dubbo配置文件自动注入
 * </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportResource(locations = {"classpath:log-consumer.xml"})
public @interface EnableSysLog {
}
