package com.leetcode;

/**
 * 156. Binary Tree Upside Down
 * 
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty, flip it
 * upside down and turn it into a tree where the original right nodes turned
 * into left leaf nodes. Return the new root.
 *
 * For example:
 * Given a binary tree {1,2,3,4,5},
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *    4
 *   / \
 * 5   2
 *    / \
 *   3   1  
 * 
 * Skill:
 * 对每个子树，left作为新root right作为新left root作为新right
 * 因为left变成了新的root，则对left以下部分进行递归，将其认为是root变成right即可
 */
public class BinaryTreeUpsideDown {
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        TreeNode left = null, right = null;
        while (root != null && root.left != null) {
            TreeNode nextRoot = root.left;
            TreeNode nextLeft = root.right;

            root.left = left;
            root.right = right;

            left = nextLeft;
            right = root;
            root = nextRoot;
        }

        if (root != null) {
            root.left = left;
            root.right = right;
        }

        return root;
    }
    
    private TreeNode helper(TreeNode root, TreeNode sibling, TreeNode parent) {
    	TreeNode newRoot = root.left;
    	TreeNode newLeft = root.right;
    	TreeNode newRight = root;
    	
    	root.left = sibling;
    	root.right = parent;
    	
    	if (newRoot != null) {
    		return helper(newRoot, newLeft, newRight);
    	} else {
    		return root;
    	}
    }
}
