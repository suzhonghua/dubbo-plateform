package com.platform.common.core.constant;

/**
 * @author szhua
 */
public interface CommonConstants {
    /**
     * 删除
     */
    String STATUS_DEL = "1";
    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 菜单树根节点
     */
    Integer MENU_TREE_ROOT_ID = -1;

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 前端工程名
     */
    String FRONT_END_PROJECT = "s-ui";

    /**
     * 后端工程名
     */
    String BACK_END_PROJECT = "s";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

    /**
     * 当前页
     */
    String CURRENT = "current";

    /**
     * size
     */
    String SIZE = "size";

    /**
     * 配置项目名称
     */
    String DUBBO_APPLICATION_NAME = "dubbo.application.name";

    /**
     * dubbo配置中心
     */
    String DUBBO_CONFIG_CENTER_ADDRESS = "dubbo.config-center.address";

    /**
     * dubbo配置中心
     */
    String DUBBO_CONFIG_CENTER_GROUP = "dubbo.config-center.group";

    /**
     * dubbo配置中心
     */
    String DUBBO_CONFIG_CENTER_NAMESPACE = "dubbo.config-center.namespace";

}
