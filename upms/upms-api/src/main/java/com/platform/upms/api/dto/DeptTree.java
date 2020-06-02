package com.platform.upms.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author szhua
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
	private String name;
}
