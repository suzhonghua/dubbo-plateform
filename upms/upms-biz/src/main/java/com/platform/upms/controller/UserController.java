package com.platform.upms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.upms.api.entity.SysUser;
import com.platform.api.upms.dto.UserInfo;
import com.platform.common.core.util.Result;
import com.platform.common.log.annotation.SysLog;
import com.platform.common.security.annotation.Inner;
import com.platform.common.security.util.SecurityUtils;
import com.platform.upms.api.dto.UserDTO;
import com.platform.upms.api.service.SysUserService;
import com.platform.upms.api.vo.UserVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author szhua
 * @date 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class UserController {

    private final SysUserService userService;

    /**
     * 获取当前用户全部信息
     *
     * @return 用户信息
     */
    @GetMapping(value = {"/info"})
    public Result<UserInfo> info() {
        String username = Objects.requireNonNull(SecurityUtils.getUser()).getUsername();
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return Result.failed("获取当前用户信息失败");
        }
        return Result.ok(userService.getUserInfo(user));
    }

    /**
     * 获取指定用户全部信息
     *
     * @return 用户信息
     */
    @Inner
    @GetMapping("/info/{username}")
    public Result<UserInfo> info(@PathVariable String username) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return Result.failed(String.format("用户信息为空 %s", username));
        }
        return Result.ok(userService.getUserInfo(user));
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/get/{id}")
    public Result<UserVO> user(@PathVariable Integer id) {
        return Result.ok(userService.getUserVoById(id));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/details/{username}")
    public Result<SysUser> user(@PathVariable String username) {
        SysUser condition = new SysUser();
        condition.setUsername(username);
        return Result.ok(userService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 删除用户信息
     *
     * @param id ID
     * @return R
     */
    @SysLog("删除用户信息")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@pms.hasPermission('sys_user_del')")
    public Result<Boolean> userDel(@PathVariable Integer id) {
        SysUser sysUser = userService.getById(id);
        return Result.status(userService.removeUserById(sysUser));
    }

    /**
     * 添加用户
     *
     * @param userDto 用户信息
     * @return success/false
     */
    @SysLog("添加用户")
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('sys_user_add')")
    public Result<Boolean> user(@RequestBody UserDTO userDto) {
        return Result.status(userService.saveUser(userDto));
    }

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return R
     */
    @SysLog("更新用户信息")
    @PutMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys_user_edit')")
    public Result<Boolean> updateUser(@Valid @RequestBody UserDTO userDto) {
        return Result.status(userService.updateUser(userDto));
    }

    /**
     * 分页查询用户
     *
     * @param page    参数集
     * @param userDTO 查询参数列表
     * @return 用户集合
     */
    @GetMapping("/page")
    public Result<IPage<UserVO>> getUserPage(Page<SysUser> page, UserDTO userDTO) {
        return Result.ok(userService.getUserWithRolePage(page, userDTO));
    }

    /**
     * 修改个人信息
     *
     * @param userDto userDto
     * @return success/false
     */
    @SysLog("修改个人信息")
    @PutMapping("/edit")
    public Result<Boolean> updateUserInfo(@Valid @RequestBody UserDTO userDto) {
        return Result.status(userService.updateUserInfo(userDto));
    }

    /**
     * @param username 用户名称
     * @return 上级部门用户列表
     */
    @GetMapping("/ancestor/{username}")
    public Result<List<SysUser>> listAncestorUsers(@PathVariable String username) {
        return Result.ok(userService.listAncestorUsersByUsername(username));
    }
}
