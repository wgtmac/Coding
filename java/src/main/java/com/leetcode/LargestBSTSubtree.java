package com.leetcode;

/**
 * 333. Largest BST Subtree
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree
 * (BST), where largest means subtree with largest number of nodes in it.
 *
 * Note:
 * A subtree must include all of its descendants.
 *
 * The return value is the subtree's size.
 *
 *  Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        int[] max = {0};
        helper(root, max);
        return max[0];
    }

    private static int[] LEFT = {Integer.MIN_VALUE, Integer.MIN_VALUE, 0};
    private static int[] RIGHT = {Integer.MAX_VALUE, Integer.MAX_VALUE, 0};
    /**
     * @return int[0]: min value in subtree
     * @return int[1]: max value in subtree
     * @return int[2]: count of nodes in subtree, -1 if not a BST
     */
    private int[] helper(TreeNode root, int[] max) {
        if (root == null) return null;

        int[] left = helper(root.left, max);
        int[] right = helper(root.right, max);

        left = (left == null ? LEFT : left);
        right = (right == null ? RIGHT : right);

        int[] ret = {0, 0, -1};

        if (left[2] >= 0 && right[2] >= 0 && left[1] < root.val
                && root.val < right[0]) {
            ret[0] = left == LEFT ? root.val : left[0];
            ret[1] = right == RIGHT ? root.val : right[1];
            ret[2] = left[2] + right[2] + 1;

            max[0] = Math.max(max[0], ret[2]);
        }

        return ret;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
