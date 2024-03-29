package com.leetcode;

/**
 * 116. Populating Next Right Pointers in Each TrieNode
 * 
 * Given a binary tree
 * struct TreeLinkNode {
 * 		TreeLinkNode *left;
 *		TreeLinkNode *right;
 *		TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be keys to NULL.
 * Initially, all next pointers are keys to NULL.
 * 
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree
 * (ie, all leaves are at the same level, and every parent has two children).
 *
 * For example,
 * Given the following perfect binary tree,
 *       1
 *     /  \
 *    2    3
 *   / \  / \
 *  4  5  6  7
 * After calling your function, the tree should look like:
 *       1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \  / \
 *  4->5->6->7 -> NULL
 * 
 * Skill: 
 * 每个node，先把自己左右相连
 * 遍历右边 再遍历左边
 * 再把自己右子树跟右边左子树连起来
 * 
 */
public class PopulatingNextRightPointersinEachNode {
	public void connect(TreeLinkNode root) {
		if (root == null) return;
		if (root.left != null) {
			root.left.next = root.right;
			if (root.next != null)
			    root.right.next = root.next.left;
			connect(root.left);
            connect(root.right);
		}	
	}

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}
