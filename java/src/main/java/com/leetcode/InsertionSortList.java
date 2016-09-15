package com.leetcode;

/**
 * 147. Insertion Sort List
 * 
 * Sort a linked list using insertion sort.
 * 
 * Skill: 
 * 选择排序：两两交换
 * 
 * 插入排序：
 * 新建一个newhead
 * 每次将待排序链表的一个结点拿出来，排入newhead里合适的位置
 * */

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE), node;
        
        while (head != null) {
            // comparison
            node = dummy;
            while (node.next != null && node.next.val <= head.val)
                node = node.next;

            // insertion
            ListNode nextNode = head.next;
            head.next = node.next;
            node.next = head;
            head = nextNode;
        }
        
        return dummy.next;
    }

    private static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; next = null; }
    }
}
