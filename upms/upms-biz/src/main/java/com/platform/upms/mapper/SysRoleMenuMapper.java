package com.platform.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.upms.api.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 新增角色
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean insertBatch(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);
}
