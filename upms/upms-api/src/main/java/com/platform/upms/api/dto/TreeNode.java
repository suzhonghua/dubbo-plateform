package com.platform.upms.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author szhua
 */
@Data
public class TreeNode {
	protected Integer id;
	protected Integer parentId;
	protected Integer sort;
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public void add(TreeNode node) {
		children.add(node);
	}
}
