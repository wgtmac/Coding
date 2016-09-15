package com.leetcode;

/**
 * 142. Linked List Cycle II
 * 
 * Given a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * Skill: 
 * 一快一慢 先相遇
 * 再起点新增一个慢指针，下次与原慢指针相遇即是
 * 
 * */

public class LinkedListCycleII {
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}

    public ListNode detectCycle(ListNode head) {
        boolean isCycle = false;
        ListNode slow = head, fast = head;
        while (!isCycle && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) break;
        }

        if (isCycle) {
            while (head != fast) {
                head = head.next;
                fast = fast.next;
            }
            return head;
        }

        return null;
    }
}
