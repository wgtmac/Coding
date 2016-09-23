package com.leetcode;

import java.util.Random;

/**
 * 382. Linked List Random Node
 *
 * Given a singly linked currList, return a random node's value from the linked currList.
 * Each node must have the same probability of being chosen.
 *
 * Follow up:
 * What if the linked currList is extremely large and its length is unknown to
 * you? Could you solve this efficiently without using extra space?
 *
 * Example:
 *
 * // Init a singly linked currList [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 *
 * // getRandom() should return either 1, 2, or 3 randomly.
 * Each element should have equal probability of returning.
 * solution.getRandom();
 *
 */
public class LinkedListRandomNode {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class Solution {
        int[] nums;
        Random rnd;

        /** @param head The linked currList's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {
            ListNode node = head;
            int len = 0;
            while (node != null) {
                node = node.next;
                len++;
            }

            nums = new int[len];
            for (int i = 0; i < len; ++i) {
                nums[i] = head.val;
                head = head.next;
            }

            rnd = new Random(System.currentTimeMillis());
        }

        /** Returns a random node's value. */
        public int getRandom() {
            return nums[rnd.nextInt(nums.length)];
        }
    }

    // Reservoir Sampling
    public class Solution2 {

        Random rnd;
        ListNode head;

        /** @param head The linked currList's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution2(ListNode head) {
            this.head = head;
            rnd = new Random(System.currentTimeMillis());
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int len = 0, value = 0;

            ListNode node = head;
            while (node != null) {
                len++;
                if (rnd.nextInt(len) == len - 1) {
                    value = node.val;
                }
                node = node.next;
            }

            return value;
        }
    }
}
