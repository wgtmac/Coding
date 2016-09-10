package com.leetcode;

/**
 * 111. Minimum Depth of Binary Tree
 * 
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 * 
 * Skill: 从上到下递归
 * 注意只有一个子树的情况
 * 
 * 最好解法：广度优先搜索
 * 
 */

public class MinimumDepthOfBinaryTree {
	public int minDepth(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null) return minDepth(root.right) + 1;
		if (root.right == null) return minDepth(root.left) + 1;
		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
