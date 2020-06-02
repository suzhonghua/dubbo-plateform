package com.platform.upms.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.upms.api.entity.SysRole;
import com.platform.upms.api.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Integer userId);

    /**
     * 通过角色ID，删除角色
     *
     * @param id
     * @return
     */
    Boolean removeRoleById(Integer id);

    /**
     * 新增角色
     *
     * @param sysRole
     * @param menuIds
     * @return
     */
    boolean add(SysRole sysRole, List<Integer> menuIds);

    /**
     * 更新角色
     *
     * @param sysRole
     * @param menuIds
     * @return
     */
    boolean updateRole(SysRole sysRole, List<Integer> menuIds);

    /**
     * 角色包含菜单列表
     *
     * @param id
     * @return
     */
    SysRoleVO get(Integer id);
}
