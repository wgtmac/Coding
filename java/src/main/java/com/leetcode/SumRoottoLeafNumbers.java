package com.leetcode;

/**
 * 129. Sum Root to Leaf Numbers
 * 
 * Given a binary tree containing digits from 0-9 only,
 * each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *     1
 *    / \
 *   2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 * 
 * Skill: 
 * 递归遍历到最底层即可加上该数字
 * */
public class SumRoottoLeafNumbers {

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
    	return helper(root, "");
    }
    
    private int helper(TreeNode root, String str) {
        if (root == null) return 0;

        int sum = 0;
    	if (root.left == null && root.right == null) {
            sum = Integer.parseInt(str + root.val);
    	}
    	
    	sum += helper(root.left, str + root.val);
    	sum += helper(root.right, str + root.val);
        return sum;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
