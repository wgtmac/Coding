package com.leetcode;

/**
 * 86. Partition List
 * 
 * Given a linked list and a value x, partition it such that
 * all nodes less than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 *  
 * Skill:
 * 先分开添加，再合并
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {      
        ListNode node = head;
        
        ListNode lHead  = new ListNode(0);
        ListNode lNode = lHead;
        ListNode rHead  = new ListNode(0);
        ListNode rNode = rHead;
        
        while (node != null) {
            if (node.val < x) {
                lNode.next = node;
                lNode = lNode.next;
            } else {
                rNode.next = node;
                rNode = rNode.next;
            }
            node = node.next;
        }
        
        lNode.next = rHead.next;
        
        // the last node should assign null to its next
        rNode.next = null;
        
        return lHead.next;
    }
	
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
}
