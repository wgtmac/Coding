package com.leetcode;

/**
 * 61. Rotate List
 * 
 * DESCRIPTION:
 * Given a list, rotate the list to the right by k places,
 * where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * 
 * Skill: 
 * 用快慢指针，先走k步
 * 若n长于len，需要取余
 * */

public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        int len = 0;
        for (ListNode node = head; node != null; node = node.next) len++;

        if (n == 0 || len == 0) return head;
        n %= len;
        
    	ListNode slow = head, fast = head;
    	while (n-- > 0)
    	    fast = fast.next;
    	
    	while (fast.next != null) {
    		slow = slow.next;
    		fast = fast.next;
    	}
    	
    	fast.next = head;
    	head = slow.next;
    	slow.next = null;
    	
    	return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null; }
    }
}
