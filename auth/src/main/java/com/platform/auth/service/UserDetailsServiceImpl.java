package com.platform.auth.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.platform.api.upms.dto.UserInfo;
import com.platform.api.upms.service.UserService;
import com.platform.common.core.constant.CommonConstants;
import com.platform.common.core.constant.SecurityConstants;
import com.platform.common.core.util.Result;
import com.platform.common.security.entity.ScUser;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户详细信息
 *
 * @author szhua
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return {@link UserDetails}
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        return getUserDetails(userService.info(username, SecurityConstants.INNER_TOKEN));
    }

    /**
     * 构建userDetails
     *
     * @param result 用户信息
     * @return {@link UserDetails}
     */
    private UserDetails getUserDetails(Result<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        // 构造security用户
        return new ScUser(info.getUserId(), info.getDeptId(), info.getUsername(), info.getPassword(),
                StrUtil.equals(info.getLockFlag(),
                        CommonConstants.STATUS_NORMAL), true, true,
                true, authorities);
    }
}
