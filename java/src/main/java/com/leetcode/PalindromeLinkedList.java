package com.leetcode;

/**
 * 234. Palindrome Linked List
 *
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        // get middle   O(n) & O(1)
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse 2nd half  O(n) & O(1)
        if (slow != null) {
            ListNode half = slow.next;
            slow.next = null;
            half = reverse(half);

            // compare   O(n) & O(1)
            return equals(head, half);
        }

        return true;
    }

    private boolean equals(ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (b.val != a.val) {
                return false;
            }
            else {
                b = b.next;
                a = a.next;
            }
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode currHead = head;
            head = head.next;
            currHead.next = newHead;
            newHead = currHead;
        }
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
