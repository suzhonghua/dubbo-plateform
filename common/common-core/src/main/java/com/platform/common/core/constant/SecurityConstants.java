package com.platform.common.core.constant;

/**
 * @author szhua
 */
public interface SecurityConstants {
    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";
    /**
     * 前缀
     */
    String PROJECT_PREFIX = "s_";

    /**
     * 项目的license
     */
    String PROJECT_LICENSE = "made by szHua";

    /**
     * 内部
     */
    String FROM_IN = "from_s_cloud_in";

    /**
     * 标志
     */
    String FROM = "from";

    /**
     * 参数token
     */
    String PARAM_TOKEN = "token";

    /**
     * 内部调用免验证
     */
    String INNER_TOKEN = "9153715f7187492882fdad3babbca5e2";

    /**
     * Authentication 存储的token
     */
    String TOKEN_VALUE = "tokenValue";


    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * 参数token
     */
    String ACCESS_TOKEN = "access_token";

    /**
     * token 类型
     */
    String BEARER = "Bearer ";

    /**
     * {bcrypt} 加密的特征码
     */
    String BCRYPT = "{bcrypt}";
    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /***
     * 资源服务器默认bean名称
     */
    String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    /**
     * 用户ID字段
     */
    String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    String DETAILS_USERNAME = "username";

    /**
     * 用户部门字段
     */
    String DETAILS_DEPT_ID = "dept_id";

    /**
     * 协议字段
     */
    String DETAILS_LICENSE = "license";
}
