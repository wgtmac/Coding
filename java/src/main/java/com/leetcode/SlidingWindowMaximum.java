package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. Sliding Window Maximum
 *
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 * Note:
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Hint:
 *
 * How about using a data structure such as deque (double-ended queue)?
 * The queue size need not be the same as the window’s size.
 * Remove redundant elements and the queue should store only elements that need to be considered.
 *
 * Solution:
 * 1. Use a TreeHMap O(nlogk)
 * 2. Use a Deque    O(n)
 *    1) update the queue so that it is non-ascending, containing all max numbers
 *    2) clean the queue if the current number to remove is same as the current max
 *    Maintains a sliding max queue
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int[] slidingMax = new int[nums.length - k + 1];

        // construct a deque to store current max
        Deque<Integer> deque = new LinkedList<Integer>();

        // init
        int i = 0;
        for (i = 0; i < k - 1; ++i) {
            update(deque, nums[i]);
        }

        // update sliding max
        for (; i < nums.length; ++i) {
            update(deque, nums[i]);
            slidingMax[i - k + 1] = deque.peekFirst();
            clean(deque, nums[i - k + 1]);
        }

        return slidingMax;
    }

    /**
     * deque only stores numbers in non-ascending order
     */
    private void update(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offerLast(num);
    }

    /**
     * when there are more than 1 same numbers, all are in the queue
     * so it is same to remove current max if it is same as the number jumping out
     * of sliding window
     */
    private void clean(Deque<Integer> deque, int num) {
        if (num == deque.peekFirst()) {
            deque.pollFirst();
        }
    }
}
