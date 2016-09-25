package com.leetcode;

import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Hint:
 * 1. Use a MinHeap, always drop the smallest one. Time: O(nlogk), space: O(k)
 * 2. Quick Find: O(nlogn)
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    // k should decrease 1
    private int quickFind(int[] nums, int start, int end, int k) {
        if (start == end) return nums[start];

        int left = start, right = end + 1;
        while (true) {
            while (nums[++left] >= nums[start])
                if (left == end) break;
            while (nums[--right] <= nums[start])
                if (right == left) break;
            if (left >= right) break;
            swap(nums, left, right);
        }

        swap(nums, start, right);

        if (right - start == k)
            return nums[right];
        else if (right - start < k)
            return quickFind(nums, right + 1, end, k - (right - start + 1));
        else
            return quickFind(nums, start, right - 1, k);
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 != index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
}
