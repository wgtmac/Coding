package com.leetcode;

/**
 * 148. Sort List
 * 
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Skill: 
 * 交换链表中两个连续node需要三步，交换两个非连续node需要四步
 *  
 * 使用归并排序
 * 找中点（快慢指针）
 * 递归分治（两边分别排序），再综合（排序两个已排序数组）
 * 
 *  注意：分的时候需要断开两个子链表之间的链接
 */

public class SortList {
   public ListNode sortList(ListNode head) {
       if (head == null || head.next == null)
           return head;
       ListNode mid = getMiddleNode(head);
       ListNode right = sortList(mid.next);
       mid.next = null;
       ListNode left = sortList(head);
       return merge(left, right);
   }

   private ListNode merge(ListNode l1, ListNode l2) {
       ListNode dummy = new ListNode(0), node = dummy;
       while (l1 != null || l2 != null) {
           if (l1 != null && l2 != null) {
               if (l1.val < l2.val) {
                   node.next = l1;
                   l1 = l1.next;
               } else {
                   node.next = l2;
                   l2 = l2.next;
               }
           } else if (l1 != null) {
               node.next = l1;
               l1 = l1.next;
           } else{
               node.next = l2;
               l2 = l2.next;
           }

           node = node.next;
       }
       return dummy.next;
   }
   
   private ListNode getMiddleNode(ListNode head) {
       if (head == null) return null;
       ListNode fast = head, slow = head;
       while (fast.next != null && fast.next.next != null) {
           slow = slow.next;
           fast = fast.next.next;
       }
       return slow;
   }

    private static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; next = null; }
    }
}
