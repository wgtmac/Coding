package com.leetcode;

/**
 * 199. Binary Tree Right Side View
 * 
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 *    1            <---
 *  /   \
 *2     3         <---
 * \     \
 *  5     4       <---
 *  You should return [1, 3, 4].
 * 
 * Skill: 
 * level order traversal
 * 每层放queue尾的结果到list里
 */

import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class BinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		
		if (root != null) queue.addLast(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			list.add(queue.peekLast().val);
			for (int i = 0; i < size; ++i) {
				TreeNode curr = queue.removeFirst();
				if (curr.left != null) queue.addLast(curr.left);
				if (curr.right != null) queue.addLast(curr.right);
			}
		}
		
		return list;
	}

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
