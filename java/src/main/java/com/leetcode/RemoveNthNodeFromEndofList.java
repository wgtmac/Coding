package com.leetcode;

/**
 * 19. Remove Nth Node From End of List
 * 
 * DESCRIPTION:
 * Given a linked currList, remove the nth node from the end of currList and return its head.
 * For example,
 * Given linked currList: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked currList becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * 
 * Skill:
 * fast先走k步，slow的地方后面就是要删除的
 */
public class RemoveNthNodeFromEndofList {
	class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // edge examination
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        if (n <= 0) return head;
                
        // locate target
        ListNode fast = dummy;
        ListNode slow = dummy;
        while ((n--) > 0) {
            if (fast.next != null) fast = fast.next;
            else return dummy.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
                
        // delete
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}
