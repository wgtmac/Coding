package com.leetcode;

/**
 * 143. Reorder List
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * Skill: 
 * 找中点，后一半反转，一一合并
 * 
 * */

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode mid = partition(head);
        merge(head, reverse(mid));
    }

    private ListNode partition(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode mid = slow.next;
        slow.next = null;       // break two lists
                
        return mid;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    
    public void merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode (0), node = dummy;
        
        while (l1 != null && l2 != null) {
            node.next = l1;
            node = node.next;
            l1 = l1.next;
            
            node.next = l2;
            node = node.next;
            l2 = l2.next;
        }
        
        if (l1 != null) node.next = l1;     
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

}
