package com.leetcode;

/**
 * 250. Count Univalue Subtrees
 *
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-val subtree means all nodes of the subtree have the same val.
 *
 * For example:
 * Given binary tree,
 *     5
 *    / \
 *   1   5
 *  / \   \
 * 5   5   5
 * return 4.
 */
public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = {0};
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode root, int[] count) {
        if (root == null) return true;

        boolean left = helper(root.left, count);
        boolean right = helper(root.right, count);
        boolean sameValue = false;

        if (left && right) {
            if (root.left != null && root.right != null) {
                if (root.left.val == root.right.val && root.left.val == root.val) {
                    sameValue = true;
                }
            } else if (root.left != null) {
                if (root.left.val == root.val) {
                    sameValue = true;
                }
            } else if (root.right != null) {
                if (root.right.val == root.val) {
                    sameValue = true;
                }
            } else {
                sameValue = true;
            }

            if (sameValue) count[0]++;
        }

        return sameValue;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
