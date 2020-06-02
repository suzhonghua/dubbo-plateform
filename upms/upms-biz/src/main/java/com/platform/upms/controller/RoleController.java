package com.platform.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.core.util.Result;
import com.platform.common.log.annotation.SysLog;
import com.platform.upms.api.entity.SysRole;
import com.platform.upms.api.service.SysRoleMenuService;
import com.platform.upms.api.service.SysRoleService;
import com.platform.upms.api.vo.RoleVo;
import com.platform.upms.api.vo.SysRoleVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author szhua
 * @date 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "role", tags = "角色管理模块")
public class RoleController {
	private final SysRoleService sysRoleService;
	private final SysRoleMenuService sysRoleMenuService;

	/**
	 * 通过ID查询角色信息
	 *
	 * @param id ID
	 * @return 角色信息
	 */
	@GetMapping("/get/{id}")
	public Result<SysRoleVO> getById(@PathVariable Integer id) {
		return Result.ok(sysRoleService.get(id));
	}

	/**
	 * 添加角色
	 *
	 * @param sysRoleVO 角色信息
	 * @return success、false
	 */
	@SysLog("添加角色")
	@PostMapping("/add")
	@PreAuthorize("@pms.hasPermission('sys_role_add')")
	public Result<Boolean> save(@Valid @RequestBody SysRoleVO sysRoleVO) {
		return Result.status(sysRoleService.add(sysRoleVO, sysRoleVO.getMenuIds()));
	}

	/**
	 * 修改角色
	 *
	 * @param sysRoleVO 角色信息
	 * @return success/false
	 */
	@SysLog("修改角色")
	@PutMapping("/update")
	@PreAuthorize("@pms.hasPermission('sys_role_edit')")
	public Result<Boolean> update(@Valid @RequestBody SysRoleVO sysRoleVO) {
		return Result.status(sysRoleService.updateRole(sysRoleVO, sysRoleVO.getMenuIds()));
	}

	/**
	 * 删除角色
	 *
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("@pms.hasPermission('sys_role_del')")
	public Result<Boolean> removeById(@PathVariable Integer id) {
		return Result.status(sysRoleService.removeRoleById(id));
	}

	/**
	 * 获取角色列表
	 *
	 * @return 角色列表
	 */
	@GetMapping("/list")
	public Result<List<SysRole>> listRoles() {
		return Result.ok(sysRoleService.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 分页查询角色信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public Result<IPage<SysRole>> getRolePage(Page<SysRole> page) {
		return Result.ok(sysRoleService.page(page, Wrappers.emptyWrapper()));
	}

	/**
	 * 更新角色菜单
	 *
	 * @param roleVo 角色对象
	 * @return success、false
	 */
	@SysLog("更新角色菜单")
	@PutMapping("/menu/update")
	@PreAuthorize("@pms.hasPermission('sys_role_perm')")
	public Result<Boolean> saveRoleMenus(@RequestBody RoleVo roleVo) {
		SysRole sysRole = sysRoleService.getById(roleVo.getRoleId());
		return Result.status(sysRoleMenuService.saveRoleMenus(sysRole.getRoleCode()
			, roleVo.getRoleId(), roleVo.getMenuIds()));
	}
}
