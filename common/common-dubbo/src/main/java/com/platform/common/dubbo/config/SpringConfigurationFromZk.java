package com.platform.common.dubbo.config;

import com.platform.common.core.constant.CommonConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.config.configcenter.DynamicConfiguration;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.NonNull;

import java.util.Map;

import static org.apache.dubbo.common.config.ConfigurationUtils.parseProperties;
import static org.apache.dubbo.common.config.configcenter.DynamicConfiguration.getDynamicConfiguration;
import static org.apache.dubbo.common.utils.StringUtils.isNotEmpty;

/**
 * @author szhua
 * <p>
 * 项目启动 加载zookeeper配置信息，后续交给dubbo动态加载
 * </p>
 */
@Slf4j
public class SpringConfigurationFromZk implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application to configure
     */
    @Override
    @SneakyThrows
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        ConfigCenterConfig configCenter = getConfigCenter(environment);
        if (!configCenter.checkOrUpdateInited()) {
            return;
        }
        DynamicConfiguration dynamicConfiguration = getDynamicConfiguration(configCenter.toUrl());
        String configContent = dynamicConfiguration.getProperties(configCenter.getConfigFile(),
                configCenter.getGroup());

        String appGroup = applicationContext.getEnvironment().getProperty(CommonConstants.DUBBO_APPLICATION_NAME);
        String appConfigContent = null;
        if (isNotEmpty(appGroup)) {
            appConfigContent = dynamicConfiguration.getProperties
                    (isNotEmpty(configCenter.getAppConfigFile()) ? configCenter.getAppConfigFile() :
                            configCenter.getConfigFile(), appGroup);
        }

        Map<String, String> globalConfig = parseProperties(configContent);
        Map<String, String> appConfig = parseProperties(appConfigContent);
        appConfig.putAll(globalConfig);
        appConfig.forEach(System::setProperty);
        log.info("zookeeper配置信息加载完毕: {}", appConfig.size());
    }

    /**
     * 获取zookeeper配置中心
     *
     * @param environment
     * @return
     */
    public ConfigCenterConfig getConfigCenter(ConfigurableEnvironment environment) {
        ConfigCenterConfig centerConfig = new ConfigCenterConfig();
        centerConfig.setAddress(environment.getProperty(CommonConstants.DUBBO_CONFIG_CENTER_ADDRESS));
        centerConfig.setGroup(environment.getProperty(CommonConstants.DUBBO_CONFIG_CENTER_GROUP));
        centerConfig.setNamespace(environment.getProperty(CommonConstants.DUBBO_CONFIG_CENTER_NAMESPACE));
        return centerConfig;
    }

}
