package com.platform.upms;

import com.platform.common.dubbo.annotation.EnableDubboProvider;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author szhua
 * 用户统一管理系统
 */
@EnableDubbo
@EnableCaching
@EnableDubboProvider
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
