package com.leetcode;

/**
 * 82. Remove Duplicates from Sorted List II
 *
 * Given a sorted linked currList, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original currList.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 *  
 * Skill:
 * 记住上一个prev
 * 如果当前与下一个相同，直到找到新的，再连接起来
 * 
 * 注意：当遇到一连串相等node结尾 需要断开
 */

public class RemoveDuplicatesFromSortedListII {

    // recursive solution
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        int val = head.val, count = 0;
        ListNode node = head;

        for (; node != null && node.val == val; ++count, node = node.next);

        ListNode nextNode = deleteDuplicates(node);

        if (count > 1)
            return nextNode;
        else {
            head.next = nextNode;
            return head;
        }
    }


    // Iterative solution
    public ListNode deleteDuplicates_iterative(ListNode head) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode node = head;
        ListNode prev = dummy;
        
        while (node != null) {
            if (node.next == null || (node.next != null && node.next.val != node.val)) {
                prev.next = node;
                prev = node;
                node = node.next;
            }
            else { // node.next != null && node.next.val == node.val
                int val = node.val;
                prev.next = null;       // it's important
                while (node != null && node.val == val) {
                    node = node.next;
                }
            }
        }
        
        return dummy.next;
    }
    
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
}
