package com.leetcode;

/**
 * 110. Balanced Binary Tree
 * 
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the
 * depth of the two subtrees of every node never differs by more than 1.
 * 
 * Skill: 
 * 递归算depth 不balance就返回-1 
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return treeHeight(root) != null;
    }
    
    private Integer treeHeight(TreeNode root) {
        if (root == null) return 0;
        
        Integer lHeight = treeHeight(root.left);
        Integer rHeight = treeHeight(root.right);
        
        if (lHeight == null || rHeight == null)
            return null;
        
        if (Math.abs(lHeight - rHeight) <= 1)
            return Math.max(lHeight, rHeight) + 1;
        else 
            return null;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
