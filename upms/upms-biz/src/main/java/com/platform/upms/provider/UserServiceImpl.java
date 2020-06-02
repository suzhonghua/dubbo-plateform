package com.platform.upms.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.upms.api.entity.SysUser;
import com.platform.api.upms.dto.UserInfo;
import com.platform.api.upms.service.UserService;
import com.platform.common.core.util.Result;
import com.platform.upms.api.service.SysUserService;
import com.platform.upms.mapper.SysUserMapper;
import lombok.AllArgsConstructor;

/**
 * @author szhua
 */
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    private final SysUserService sysUserService;

    /**
     * 获取认证用户信息
     *
     * @param username 用户名
     * @return {@link UserInfo}
     */
    @Override
    public Result<UserInfo> info(String username, String token) {
        SysUser user = getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return Result.failed(String.format("用户信息为空 %s", username));
        }
        return Result.ok(sysUserService.getUserInfo(user));
    }
}
