package com.platform.upms.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.upms.api.entity.SysRoleMenu;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

	/**
	 * 更新角色菜单
	 *
	 * @param role
	 * @param roleId  角色
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	boolean saveRoleMenus(String role, Integer roleId, String menuIds);
}
