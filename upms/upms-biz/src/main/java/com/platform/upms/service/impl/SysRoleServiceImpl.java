package com.platform.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.core.constant.CacheConstants;
import com.platform.upms.api.entity.SysRole;
import com.platform.upms.api.entity.SysRoleMenu;
import com.platform.upms.api.service.SysRoleService;
import com.platform.upms.api.vo.SysRoleVO;
import com.platform.upms.mapper.SysRoleMapper;
import com.platform.upms.mapper.SysRoleMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysRoleMapper sysRoleMapper;

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }

    /**
     * 通过角色ID，删除角色,并清空角色菜单缓存
     *
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeRoleById(Integer id) {
        sysRoleMenuMapper.delete(Wrappers
                .<SysRoleMenu>update().lambda()
                .eq(SysRoleMenu::getRoleId, id));
        return this.removeById(id);
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @param menuIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysRole sysRole, List<Integer> menuIds) {
        boolean save = save(sysRole);
        boolean batch = sysRoleMenuMapper.insertBatch(sysRole.getRoleId(), menuIds);
        return save && batch;
    }

    /**
     * 更新角色
     *
     * @param sysRole
     * @param menuIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(SysRole sysRole, List<Integer> menuIds) {
        boolean b = updateById(sysRole);
        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>update().lambda()
                .eq(SysRoleMenu::getRoleId, sysRole.getRoleId()));
        boolean b1 = sysRoleMenuMapper.insertBatch(sysRole.getRoleId(), menuIds);
        return b && b1;
    }

    /**
     * 角色包含菜单列表
     *
     * @param id
     * @return
     */
    @Override
    public SysRoleVO get(Integer id) {
        return sysRoleMapper.get(id);
    }
}
