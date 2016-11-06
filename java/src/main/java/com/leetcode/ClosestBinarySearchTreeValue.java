package com.leetcode;

/**
 * 270. Closest Binary Search Tree Value
 *
 * Given a non-empty binary search tree and a target value, find the value in the
 * BST that is closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest
 * to the target.
 */
public class ClosestBinarySearchTreeValue {

    // assume root is not null
    public int closestValue(TreeNode root, double target) {
        double diff = Math.abs(root.val - target);
        int ans = root.val;

        if (root.val == target) {
            ans = root.val;
        } else if (root.val > target) {
            if (root.left != null) {
                int val = closestValue(root.left, target);
                if (Math.abs(val - target) < diff) {
                    ans = val;
                }
            }
        } else {
            if (root.right != null) {
                int val = closestValue(root.right, target);
                if (Math.abs(val - target) < diff) {
                    ans = val;
                }
            }
        }

        return ans;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
