package com.platform.upms.controller;

import com.platform.common.core.util.Result;
import com.platform.common.log.annotation.SysLog;
import com.platform.upms.api.dto.DeptTree;
import com.platform.upms.api.entity.SysDept;
import com.platform.upms.api.service.SysDeptService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", tags = "部门管理模块")
public class DeptController {

    private final SysDeptService sysDeptService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDept
     */
    @GetMapping("/get/{id}")
    public Result<SysDept> getById(@PathVariable Integer id) {
        return Result.ok(sysDeptService.getById(id));
    }


    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public Result<List<DeptTree>> listDeptTrees() {
        return Result.ok(sysDeptService.listDeptTrees());
    }

    /**
     * 返回当前用户树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/user-tree")
    public Result<List<DeptTree>> listCurrentUserDeptTrees() {
        return Result.ok(sysDeptService.listCurrentUserDeptTrees());
    }

    /**
     * 添加
     *
     * @param sysDept 实体
     * @return success/false
     */
    @SysLog("添加部门")
    @PostMapping("/add")
    @PreAuthorize("@pms.hasPermission('sys_dept_add')")
    public Result<Boolean> save(@Valid @RequestBody SysDept sysDept) {
        return Result.status(sysDeptService.saveDept(sysDept));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @SysLog("删除部门")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@pms.hasPermission('sys_dept_del')")
    public Result<Boolean> removeById(@PathVariable Integer id) {
        return Result.status(sysDeptService.removeDeptById(id));
    }

    /**
     * 编辑
     *
     * @param sysDept 实体
     * @return success/false
     */
    @SysLog("编辑部门")
    @PutMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys_dept_edit')")
    public Result<Boolean> update(@Valid @RequestBody SysDept sysDept) {
        sysDept.setUpdateTime(LocalDateTime.now());
        return Result.status(sysDeptService.updateDeptById(sysDept));
    }
}
