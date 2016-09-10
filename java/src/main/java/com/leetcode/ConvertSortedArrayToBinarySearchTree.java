package com.leetcode;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * 
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * 
 * Skill: 
 * 找中点，作为根，左右分别递归调用
 */

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null) return null;
        return BST(num, 0, num.length - 1);
    }
    
    public TreeNode BST(int[] num, int start, int end) {
        if (start > end)
            return null;
        else if (start == end)
            return new TreeNode(num[start]);
        
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = BST(num, start, mid - 1);
        root.right = BST(num, mid + 1, end);
        return root;
    }

	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
}
