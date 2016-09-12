package com.leetcode;

/**
 * 112. Path Sum
 * 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *   5
 *   / \
 *   4   8
 *   /   / \
 *   11  13  4
 *   /  \    / \
 *   7    2  5   1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 * Skill: 从上到下递归
 */

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return helper(root, sum, 0);
    }
    
    private boolean helper(TreeNode root, int targetSum, int currSum) {
        if (root.left == null && root.right == null)
            return currSum + root.val == targetSum;
        
        boolean ret = false;
        if (root.left != null)
            ret = helper(root.left, targetSum, currSum + root.val);

        if (!ret && root.right != null)
            ret = helper(root.right, targetSum, currSum + root.val);
 
        return ret;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
