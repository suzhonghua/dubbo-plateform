package com.platform.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.upms.api.entity.SysMenu;
import com.platform.upms.api.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色编号查询菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<MenuVO> listMenusByRoleId(Integer roleId);

    /**
     * 通过角色ID查询权限
     *
     * @param roleIds Ids
     * @return
     */
    List<String> listPermissionsByRoleIds(String roleIds);

    /**
     * 角色 菜单id列表
     *
     * @param roleId
     * @return
     */
    List<Integer> listMenuIdsByRoleId(Integer roleId);

}
