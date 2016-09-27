package com.leetcode;

/**
 * 230. Kth Smallest Element in a BST
 *
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to
 * find the kth smallest frequently? How would you optimize the kthSmallest routine?
 *
 * Hint:
 * Try to utilize the property of a BST.
 * What if you could modify the BST node's structure?
 * The optimal runtime complexity is O(height of BST).
 */
public class KthSmallestElementInABST {
    private int index;
    public int kthSmallest(TreeNode root, int k) {
        index = 0;
        return helper(root, k);
    }

    public int helper(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left, k);
        if (index == k) {
            return left;
        }

        if (++index == k) {
            return root.val;
        }

        return helper(root.right, k);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
