package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k
 *
 * Given an array nums and a target value k, find the maximum length of a subarray
 * that sums to k. If there isn't one, return 0 instead.
 *
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed
 * integer range.
 *
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 *
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 *
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();  // sum, 0->index

        int longest = 0;
        for (int i = 0, sum = 0; i < nums.length; ++i) {
            sum += nums[i];

            if (sum == k)
                longest = i + 1;

            if (map.containsKey(sum - k))
                longest = Math.max(longest, i - map.get(sum - k));

            if (!map.containsKey(sum))
                map.put(sum, i);
        }

        return longest;
    }
}
