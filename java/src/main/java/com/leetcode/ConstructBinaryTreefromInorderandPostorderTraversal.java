package com.leetcode;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * 
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * Skill: 
 * pos:  (B) (C)  A
 * in:    (B)   A  (C)
 * */
import java.util.HashMap;

public class ConstructBinaryTreefromInorderandPostorderTraversal {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if (inorder == null || inorder.length == 0) return null;
    	
    	for (int i = 0; i < inorder.length; i++)
    		map.put(inorder[i], i);

    	return helper(postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    
    TreeNode helper(int[] postorder, int i_start, int i_end, int p_start, int p_end) {
    	if (i_start > i_end) return null;
    	TreeNode node = new TreeNode(postorder[p_end]);
    	
    	node.left = helper(postorder, i_start, map.get(postorder[p_end]) - 1, p_start, p_start + map.get(postorder[p_end]) - 1 - i_start);
    	node.right = helper(postorder, map.get(postorder[p_end]) + 1, i_end, p_start + map.get(postorder[p_end]) - i_start, p_end - 1);
    	
    	return node;
    }

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
