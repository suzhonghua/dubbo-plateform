package com.platform.upms.api.vo;

import com.platform.upms.api.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author szhua
 */
@ApiModel(value = "角色包含菜单列表")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysRoleVO extends SysRole {
    private static final long serialVersionUID = -1254395592001405504L;
    /**
     * 菜单列表
     */
    @ApiModelProperty(value = "菜单列表")
    private List<Integer> menuIds;

}
