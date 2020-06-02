package com.platform.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.core.constant.FieldConstants;
import com.platform.upms.api.entity.SysLog;
import com.platform.common.core.util.Result;
import com.platform.common.security.annotation.Inner;
import com.platform.upms.api.service.SysLogService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class LogController {
    private final SysLogService sysLogService;

    /**
     * 简单分页查询
     *
     * @param page   分页对象
     * @param sysLog 系统日志
     * @return
     */
    @GetMapping("/page")
    public Result<IPage<SysLog>> getLogPage(Page<SysLog> page, SysLog sysLog) {
        return Result.ok(sysLogService.page(page, Wrappers.query(sysLog).orderByDesc(FieldConstants.CREATE_TIME)));
    }

    /**
     * 删除日志
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/get/{id}")
    @PreAuthorize("@pms.hasPermission('sys_log_del')")
    public Result<Boolean> removeById(@PathVariable Long id) {
        return Result.status(sysLogService.removeById(id));
    }

    /**
     * 插入日志
     *
     * @param sysLog 日志实体
     * @return success/false
     */
    @Inner
    @PostMapping
    public Result<Boolean> save(@Valid @RequestBody SysLog sysLog) {
        return Result.status(sysLogService.save(sysLog));
    }

}
