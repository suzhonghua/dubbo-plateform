package com.platform.upms.api.dto;

import com.platform.upms.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author szhua
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
	private static final long serialVersionUID = -1246884595862284L;
	/**
	 * 角色ID
	 */
	private List<Integer> role;

	private Integer deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;
}
