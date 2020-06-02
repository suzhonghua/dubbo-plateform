package com.platform.upms.api.dto;

import com.platform.upms.api.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author szhua
 * 角色Dto
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {
	/**
	 * 角色部门Id
	 */
	private Integer roleDeptId;

	/**
	 * 部门名称
	 */
	private String deptName;
}
