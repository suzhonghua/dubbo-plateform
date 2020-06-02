package com.platform.upms.controller;

import com.platform.common.core.util.Result;
import com.platform.common.log.annotation.SysLog;
import com.platform.common.security.util.SecurityUtils;
import com.platform.upms.api.dto.MenuTree;
import com.platform.upms.api.entity.SysMenu;
import com.platform.upms.api.service.SysMenuService;
import com.platform.upms.api.vo.MenuVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author szhua
 * @date 2017/10/31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class MenuController {
    private final SysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @param parentId 父节点ID
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public Result<List<MenuTree>> getUserMenu(Integer parentId) {
        // 获取符合条件的菜单
        Set<MenuVO> all = new HashSet<>();
        SecurityUtils.getRoles().forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
        return Result.ok(sysMenuService.filterMenu(all, parentId));
    }

    /**
     * 返回树形菜单集合
     *
     * @param lazy     是否是懒加载
     * @param parentId 父节点ID
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public Result<List<MenuTree>> getTree(boolean lazy, Integer parentId) {
        return Result.ok(sysMenuService.treeMenu(lazy, parentId));
    }

    /**
     * 返回角色的菜单集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public Result<List<Integer>> getRoleTree(@PathVariable Integer roleId) {
        return Result.ok(sysMenuService.findMenuByRoleId(roleId)
                .stream()
                .map(MenuVO::getMenuId)
                .collect(Collectors.toList()));
    }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/get/{id}")
    public Result<SysMenu> getById(@PathVariable Integer id) {
        return Result.ok(sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单信息
     * @return success/false
     */
    @SysLog("新增菜单")
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('sys_menu_add')")
    public Result<Boolean> save(@Valid @RequestBody SysMenu sysMenu) {
        return Result.status(sysMenuService.save(sysMenu));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@pms.hasPermission('sys_menu_del')")
    public Result<Boolean> removeById(@PathVariable Integer id) {
        return Result.status(sysMenuService.removeMenuById(id));
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys_menu_edit')")
    public Result<Boolean> update(@Valid @RequestBody SysMenu sysMenu) {
        return Result.status(sysMenuService.updateMenuById(sysMenu));
    }

}
