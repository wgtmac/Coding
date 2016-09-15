package com.leetcode;

/**
 * 144. Binary Tree Preorder Traversal
 * 
 * DESCRIPTION:
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * Skill: 
 * 先pop root, 然后push right，最后push left
 * 
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<> ();
		Stack<TreeNode> stack = new Stack<> ();
		
		if (root == null) return list;
		stack.push(root);
		
		while (!stack.empty()) {
		    root = stack.pop();
		    list.add(root.val);
		    if (root.right != null)
		        stack.push(root.right);
		    if (root.left != null)
		        stack.push(root.left);
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
