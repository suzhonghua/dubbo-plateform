package com.platform.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.core.util.Result;
import com.platform.common.log.annotation.SysLog;
import com.platform.upms.api.entity.SysOauthClientDetails;
import com.platform.upms.api.service.SysOauthClientDetailsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author szhua
 * @since 2018-05-15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Api(value = "client", tags = "客户端管理模块")
public class OauthClientDetailsController {
	private final SysOauthClientDetailsService sysOauthClientDetailsService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysOauthClientDetails
	 */
	@GetMapping("/get/{id}")
	public Result<SysOauthClientDetails> getById(@PathVariable Integer id) {
		return Result.ok(sysOauthClientDetailsService.getById(id));
	}


	/**
	 * 简单分页查询
	 *
	 * @param page                  分页对象
	 * @param sysOauthClientDetails 系统终端
	 * @return
	 */
	@GetMapping("/page")
	public Result<IPage<SysOauthClientDetails>> getOauthClientDetailsPage(Page<SysOauthClientDetails> page, SysOauthClientDetails sysOauthClientDetails) {
		return Result.ok(sysOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
	}

	/**
	 * 添加
	 *
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@SysLog("添加终端")
	@PostMapping("/add")
	@PreAuthorize("@pms.hasPermission('sys_client_add')")
	public Result<Boolean> add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
		return Result.status(sysOauthClientDetailsService.save(sysOauthClientDetails));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除终端")
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("@pms.hasPermission('sys_client_del')")
	public Result<Boolean> removeById(@PathVariable String id) {
		return Result.status(sysOauthClientDetailsService.removeClientDetailsById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@SysLog("编辑终端")
	@PutMapping("/update")
	@PreAuthorize("@pms.hasPermission('sys_client_edit')")
	public Result<Boolean> update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
		return Result.status(sysOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
	}
}
