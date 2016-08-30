package com.leetcode;

/**
 * 2. Add Two Numbers
 *
 * DESCRIPTION:
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked currList.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * 
 */
public class AddTwoNumbers {
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        int carry = 0, left, right, sum;
        while (l1 != null || l2 != null) {
            left = right = 0;

            if (l1 != null) {
                left = l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                right = l2.val;
                l2 = l2.next;
            }

            sum = left + right + carry;

            if (sum >= 10) {
                carry = 1;
                node.next = new ListNode(sum - 10);
            } else {
                carry = 0;
                node.next = new ListNode(sum);
            }

            node = node.next;
        }
        
        if (carry == 1) {
            node.next = new ListNode(1);
        }
        
        return dummy.next;
    }
}
