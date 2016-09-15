package com.leetcode;

/**
 * 141. Linked List Cycle
 * 
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * Skill: 
 * 一快一慢 相遇
 * 
 * */

public class LinkedListCycle {
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
	
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
                
            if (slow == fast) return true;
        }
        
        return false;
    }
}
