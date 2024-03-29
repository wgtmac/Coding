package com.leetcode;

/**
 * 337. House Robber III
 *
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called the "root." Besides the root, each house
 * has one and only one parent house. After a tour, the smart thief realized
 * that "all houses in this place forms a binary tree". It will automatically
 * contact the police if two directly-linked houses were broken into on the same
 * night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int rob(TreeNode root) {
        int[] robbed = visit(root);
        return Math.max(robbed[0], robbed[1]);
    }

    private static int[] ZERO = {0 , 0};

    // max robbed, int[2]: 0 rob, 1 skip
    private int[] visit(TreeNode root) {
        if (root == null) return ZERO;

        int[] left = visit(root.left);
        int[] right = visit(root.right);

        int robCurrent = root.val + left[1] + right[1];
        int skipCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] {robCurrent, skipCurrent};
    }
}
