package com.platform.common.dubbo.annotation;

import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.*;

/**
 * @author szhua
 * <p>
 * consumer.xml自动注入
 * </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportResource(locations = {"classpath:consumer.xml"})
public @interface EnableDubboConsumer {
}
