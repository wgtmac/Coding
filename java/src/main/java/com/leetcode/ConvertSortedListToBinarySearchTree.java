package com.leetcode;

import java.util.List;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * 
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * Trick: inorder traversal建立
 */

public class ConvertSortedListToBinarySearchTree {
    private TreeNode sortedListToBST(ListNode node, int len) {
        if (len == 0) return null;
        if (len == 1) return new TreeNode(node.val);

        int mid = len / 2, index = 0;
        ListNode head = node;
        while (index++ != mid)
            node = node.next;

        TreeNode root = new TreeNode(node.val);
        root.left = sortedListToBST(head, mid);
        root.right = sortedListToBST(node.next, len - mid - 1);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            len++;
        }
        return sortedListToBST(head, len);
    }

	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}

	private static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
}
