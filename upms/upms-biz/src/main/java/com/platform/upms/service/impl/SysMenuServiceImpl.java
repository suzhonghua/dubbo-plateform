package com.platform.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.core.constant.CacheConstants;
import com.platform.common.core.constant.CommonConstants;
import com.platform.common.core.constant.enums.MenuTypeEnum;
import com.platform.upms.api.dto.MenuTree;
import com.platform.upms.api.entity.SysMenu;
import com.platform.upms.api.entity.SysRoleMenu;
import com.platform.upms.api.service.SysMenuService;
import com.platform.upms.api.vo.MenuVO;
import com.platform.upms.api.vo.TreeUtil;
import com.platform.upms.mapper.SysMenuMapper;
import com.platform.upms.mapper.SysRoleMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author szhua
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public List<MenuVO> findMenuByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public boolean removeMenuById(Integer id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query()
                .lambda().eq(SysMenu::getParentId, id));
        Assert.isTrue(CollUtil.isEmpty(menuList), "菜单含有下级不能删除");

        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query()
                .lambda().eq(SysRoleMenu::getMenuId, id));
        //删除当前菜单及其子菜单
        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }

    /**
     * 构建树查询
     * 1. 不是懒加载情况，查询全部
     * 2. 是懒加载，根据parentId 查询
     * 2.1 父节点为空，则查询ID -1
     *
     * @param lazy     是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<MenuTree> treeMenu(boolean lazy, Integer parentId) {
        if (!lazy) {
            return TreeUtil.buildTree(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery()
                    .orderByAsc(SysMenu::getSort)), CommonConstants.MENU_TREE_ROOT_ID);
        }

        Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.buildTree(baseMapper
                .selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent)
                        .orderByAsc(SysMenu::getSort)), parent);
    }

    /**
     * 查询菜单
     *
     * @param all      全部菜单
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<MenuTree> filterMenu(Set<MenuVO> all, Integer parentId) {
        List<MenuTree> menuTreeList = all.stream()
                .filter(vo -> MenuTypeEnum.LEFT_MENU.getType().equals(vo.getType()))
                .map(MenuTree::new).collect(Collectors.toList());
        Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(menuTreeList, parent);
    }
}
