package com.platform.api.upms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author szhua
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "主键id")
    private Integer userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 随机盐
     */
    @JsonIgnore
    @ApiModelProperty(value = "随机盐")
    private String salt;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 锁定标记
     */
    @ApiModelProperty(value = "锁定标记")
    private String lockFlag;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    /**
     * 部门ID
     */
    @ApiModelProperty(value = "用户所属部门id")
    private Integer deptId;

    /**
     * 0-正常，1-删除
     */
    @ApiModelProperty(value = "删除标记  0-正常，1-删除")
    private String delFlag;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Integer[] roles;
}
