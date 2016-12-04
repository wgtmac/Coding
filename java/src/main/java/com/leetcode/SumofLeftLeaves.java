package com.leetcode;

/**
 * 404. Sum of Left Leaves
 *
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Return 24.
 *
 */
public class SumofLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }

    private int helper(TreeNode root, boolean isLeftChild) {
        if (root == null)
            return 0;
        if (null == root.left && null == root.right)
            return isLeftChild ? root.val : 0;

        return helper(root.left, true) + helper(root.right, false);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
