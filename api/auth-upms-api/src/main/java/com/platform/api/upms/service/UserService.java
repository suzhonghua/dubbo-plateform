package com.platform.api.upms.service;

import com.platform.api.upms.dto.UserInfo;
import com.platform.common.core.util.Result;

/**
 * @author szhua
 */
public interface UserService {

    /**
     * 获取认证用户信息
     *
     * @param username 用户名
     * @param token    请求token
     * @return {@link UserInfo}
     */
    Result<UserInfo> info(String username, String token);

}
