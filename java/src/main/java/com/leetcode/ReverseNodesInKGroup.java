package com.leetcode;

/**
 * 25. Reverse Nodes in k-Group
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * Skill:
 * 标记出start和end，中间的截取出来
 * reverse中间的
 * 连接起来
 */
public class ReverseNodesInKGroup {
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; next = null; }
	}
	
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode lastNodeInPrevGroup = dummy;  // next node is current 1st
        ListNode firstNodeInNextGroup;         // prev node is current kth
		ListNode currNode, placeToInsert, nextNode;
        
        for (int len = getLen(head); len >= k; len -= k) {
        	firstNodeInNextGroup = getNextKthNode(lastNodeInPrevGroup.next, k);

        	currNode = lastNodeInPrevGroup.next;
			placeToInsert = firstNodeInNextGroup;
        	for (int i = 0; i < k; i++) {
				nextNode = currNode.next;
        		currNode.next = placeToInsert;

        		if (i == 0)
        			firstNodeInNextGroup = currNode;

        		placeToInsert = currNode;
        		currNode = nextNode;
        	}
        	
        	lastNodeInPrevGroup.next = placeToInsert;
        	lastNodeInPrevGroup = firstNodeInNextGroup;
        }
        
        return dummy.next;
    }
    
    public ListNode getNextKthNode (ListNode head, int k) {
    	for (int i = 0; i < k; i++, head = head.next);
    	return head;
    }
    
   public int getLen(ListNode head) {
	   int len = 0;
	   for (; head != null; len++, head = head.next);
	   return len;
   }
}
