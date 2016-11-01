package com.leetcode;

/**
 * 298. Binary Tree Longest Consecutive Sequence 
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path
 * need to be from parent to child (cannot be the reverse).
 *
 * For example,
 *
 *     1
 *      \
 *      3
 *     / \
 *    2  4
 *        \
 *        5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *      2
 *       \
 *       3
 *      /
 *     2
 *    /
 *   1
 * Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 *
 */
public class BinaryTreeLongestConsecutiveSequence {
    int longestConsecutive(TreeNode root) {
        int[] max = {0};
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) return 0;

        int left = helper(root.left, max);
        int right = helper(root.right, max);

        if (left != 0 && root.val + 1 != root.left.val) {
            left = 0;
        }
        if (right != 0 && root.val + 1 != root.right.val) {
            right = 0;
        }

        max[0] = Math.max(max[0], 1 + Math.max(left, right));
        return 1 + Math.max(left, right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
