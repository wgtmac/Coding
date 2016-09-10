package com.leetcode;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * Skill: 
 * pre:  A (B) (C)
 * in:  (B)   A  (C)
 * */
import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if (preorder == null || preorder.length == 0)
    		return null;

		Map<Integer, Integer> inorderVal2Idx = new HashMap<>();
    	for (int i = 0; i < inorder.length; i++)
    		inorderVal2Idx.put(inorder[i], i);

    	return helper(preorder, 0, preorder.length - 1, 0, inorderVal2Idx);
    }
    
    TreeNode helper(int[] preorder, int preStart, int preEnd, int inStart, Map<Integer, Integer> map) {
    	if (preStart > preEnd) return null;
    	TreeNode node = new TreeNode(preorder[preStart]);
		int leftLength = map.get(preorder[preStart]) - inStart;
    	node.left = helper(preorder, preStart + 1, leftLength + preStart, inStart, map);
    	node.right = helper(preorder, leftLength + preStart + 1, preEnd, map.get(preorder[preStart]) + 1, map);
    	return node;
    }

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
