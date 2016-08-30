package com.leetcode;

/**
 * 83. Remove Duplicates from Sorted List
 * 
 * DESCRIPTION:
 * Given a sorted linked currList, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 *  
 * Skill:
 * 记住上一个prev
 * 如果当前与下一个相同，跳过下面的，直到找到新的，再连接起来
 */
public class RemoveDuplicatesFromSortedList {
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
	
	// 检查当前与下一个
    public ListNode deleteDuplicates (ListNode head) {
        if (head == null) return null;

        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val)
                node.next = node.next.next;
            else
                node = node.next;

        }
        return head;
    }
}
