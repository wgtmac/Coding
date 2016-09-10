package com.leetcode;

/**
 * 98. Validate Binary Search Tree
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * Skill: 递归
 * 判断左右子树是不是BST
 * 
 * On空间复杂度做法就是inorder遍历
 * 
 * 可以在参数里规定该函数所有结点满足的最小最大值
 * 
 */

public class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean valid(TreeNode p, long low, long high) {
        if (p == null) return true;
        return p.val < high && low < p.val &&
                valid(p.left, low, p.val) && valid(p.right, p.val, high);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
