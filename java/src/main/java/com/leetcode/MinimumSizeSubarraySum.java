package com.leetcode;

import java.util.Arrays;

/**
 * 209. Minimum Size Subarray Sum
 *
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a subarray of which the sum ≥ s. If there isn't one, return
 * 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 *
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n).
 *
 * Hint: sub-array means consecutive elements are required.
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0, sum = 0, cnt = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            while (sum >= s && start <= i) {
                cnt = Math.min(cnt, i - start + 1);
                sum -= nums[start++];
            }
        }
        return cnt == Integer.MAX_VALUE ? 0 : cnt;
    }
}
