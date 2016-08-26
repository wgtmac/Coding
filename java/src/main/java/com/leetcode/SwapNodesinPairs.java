package com.leetcode;

/**
 * 24. Swap Nodes in Pairs
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. 
 * You may not modify the values in the list, only nodes itself can be changed.
 * 
 * Skill: 
 * 
 *  注意：分的时候需要断开两个子链表之间的链接
 *  可优化掉i
 * */

public class SwapNodesinPairs {
	class ListNode {
		final public int val;
		public ListNode next;
		public ListNode(int x) { val = x; next = null; }
	}
	
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode node =dummy;
		ListNode prev = null;
		int i = 0;
		while (node != null) {
			if (i % 2 != 0) {
				if (node.next == null) break;
				prev.next = node.next;
				node.next = prev.next.next;
				prev.next.next = node;
				
				prev = prev.next;
			} else {
				prev = node;
				node = node.next;
			}
			i++;
		}
		
		return dummy.next;
	}
}
