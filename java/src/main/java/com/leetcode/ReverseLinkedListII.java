package com.leetcode;

/**
 * 92. Reverse Linked List II
 * 
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * 
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * 
 * Skill:
 * 先分割，再reverse
 * 记住分割点前后node
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode node = dummy, lEnd = null, mStart = null, mEnd = null;
        
        int i = 0;
        while (node != null) {
            if (i <= m - 1) {
                lEnd = node;
                node = node.next;
            } else if (m == i) {
                mStart = node;
                mEnd = node;
                node = node.next;
            } else if (m < i && i <= n){
                ListNode nodeNext = node.next;
                node.next = mStart;
                mStart = node;
                node = nodeNext;
                if (i == n)
                    break;
            }
            i++;
        }
        
        lEnd.next = mStart;
        mEnd.next = node;
        
        return dummy.next;
    }
    
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
}
