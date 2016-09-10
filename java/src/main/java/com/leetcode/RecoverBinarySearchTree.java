package com.leetcode;

/**
 * 99. Recover Binary Search Tree
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note: A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 * 
 * Skill:
 * 中序遍历，保存上一次结果
 * 如果是逆序，存下prev和curr到list
 * list最终可能有两种情况：若两个交换点不相邻，则list有4个分别错对对错，若相邻，则交换他俩
 * 
 * 四个的情况  肯定是    a+m   a  b   b-n   后面大的数放到前面来了所以是前面一组大的和后面一组小的错了
 */

import java.util.ArrayList;

public class RecoverBinarySearchTree {
	private TreeNode prev = null;
	private ArrayList<TreeNode>list;
	
    public void recoverTree(TreeNode root) {
    	list = new ArrayList<>();
    	inorder_traverse(root);
    	TreeNode node1, node2;
    	if (list.size() == 4) {
    		node1 = list.get(0);
    		node2 = list.get(3);
    	} else {
    		node1 = list.get(0);
    		node2 = list.get(1);
    	}
    	node1.val ^= node2.val;
    	node2.val ^= node1.val;
    	node1.val ^= node2.val;
    }
    
    private void inorder_traverse(TreeNode root) {
    	if (root == null) return;
    	
    	inorder_traverse(root.left);
    	if (prev!= null && prev.val >= root.val) {
    		list.add(prev);
    		list.add(root);
    	}
    	prev = root;
    	inorder_traverse(root.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
