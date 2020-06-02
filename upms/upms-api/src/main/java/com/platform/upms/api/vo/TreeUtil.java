package com.platform.upms.api.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.platform.upms.api.entity.SysMenu;
import com.platform.upms.api.dto.MenuTree;
import com.platform.upms.api.dto.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author szhua
 */
@UtilityClass
public class TreeUtil {
	/**
	 * 两层循环实现建树
	 *
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<>();

		for (T treeNode : treeNodes) {

			if (root.equals(treeNode.getParentId())) {
				trees.add(treeNode);
				trees.sort(Comparator.comparing(t -> ObjectUtil.isNotNull(t.getSort()) ? t.getSort() : 0));
			}

			for (T it : treeNodes) {
				if (it.getParentId().equals(treeNode.getId())) {
					if (treeNode.getChildren() == null) {
						treeNode.setChildren(new ArrayList<>());
					}
					treeNode.add(it);
					List<TreeNode> children = treeNode.getChildren();
					if (CollUtil.isNotEmpty(children) && children.size() > 1) {
						children.sort(Comparator.comparing(t -> ObjectUtil.isNotNull(t.getSort()) ? t.getSort() : 0));
					}
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 *
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
				trees.sort(Comparator.comparing(t -> ObjectUtil.isNotNull(t.getSort()) ? t.getSort() : 0));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 *
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId().equals(it.getParentId())) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.add(findChildren(it, treeNodes));
				List<TreeNode> children = treeNode.getChildren();
				if (CollUtil.isNotEmpty(children) && children.size() > 1) {
					children.sort(Comparator.comparing(t -> ObjectUtil.isNotNull(t.getSort()) ? t.getSort() : 0));
				}
			}
		}
		return treeNode;
	}

	/**
	 * 通过sysMenu创建树形节点
	 *
	 * @param menus
	 * @param root
	 * @return
	 */
	public List<MenuTree> buildTree(List<SysMenu> menus, int root) {
		List<MenuTree> trees = new ArrayList<>();
		MenuTree node;
		for (SysMenu menu : menus) {
			node = new MenuTree();
			node.setId(menu.getMenuId());
			node.setParentId(menu.getParentId());
			node.setName(menu.getName());
			node.setPath(menu.getPath());
			node.setPermission(menu.getPermission());
			node.setLabel(menu.getName());
			node.setIcon(menu.getIcon());
			node.setType(menu.getType());
			node.setSort(menu.getSort());
			node.setHasChildren(true);
			node.setKeepAlive(menu.getKeepAlive());
			node.setHidden(menu.getHidden());
			trees.add(node);
		}
		return TreeUtil.build(trees, root);
	}
}
