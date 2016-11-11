package com.leetcode;

import java.util.Stack;

/**
 * 369. Plus One Linked List
 *
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 * Input:
 * 1->2->3
 *
 * Output:
 * 1->2->4
 */
public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        int carry = 1;
        ListNode node = null;
        while (!stack.isEmpty()) {
            node = stack.pop();
            node.val += carry;
            if (node.val == 10) {
                node.val = 0;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = node;
            node = newHead;
        }

        return node;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
