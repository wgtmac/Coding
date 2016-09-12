package com.leetcode;

/**
 * 124. Binary Tree Maximum Path Sum
 * 
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * 
 * For example:
 * Given the below binary tree,
 *        1
 *       / \
 *      2   3
 * Return 6.
 * 
 * Skill: 
 * 递归计算
 * 需要注意：不一定要经过根root，里面的左右子树和局部root的回路也可以
 * 注意二： 因为可以是中间任意结点，可以不取子树，因此如果子树和为负数，则可以不取（设为0）
 * */

public class BinaryTreeMaximumPathSum {
    // record the max path sum
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        maxSum = Integer.MIN_VALUE;
        maxPath(root);
        return maxSum;
    }
    
    // return the max sum from root to bottom (maybe only helper nodes are useful)
    private int maxPath(TreeNode root) {
        if (root == null) return 0;
        
        // need to notice that, we do not have to go from bottom to bottom via root
        // halfway is OK
        // so it is important to check any value that is lower than ZERO!
        int lMax = Math.max(0, maxPath(root.left));			// 如果子树是负数，不如不取
        int rMax = Math.max(0, maxPath(root.right));
        
        if (lMax + rMax + root.val > maxSum)
            maxSum = lMax + rMax + root.val;
        
        return Math.max(lMax, rMax) + root.val;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
