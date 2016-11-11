package com.leetcode;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 327. Count of Range Sum
 *
 Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

 Note:
 A naive algorithm of O(n^2) is trivial. You MUST do better than that.

 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class CountofRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0)
                sum[i] = nums[i];
            else
                sum[i] = sum[i - 1] + nums[i];
        }

        int cnt = 0;
        long rangeSum = 0;
        for (int end = 0; end < nums.length; ++end) {
            for (int start = 0; start <= end; ++start) {
                if (start == 0)
                    rangeSum = sum[end];
                else
                    rangeSum = sum[end] - sum[start - 1];
                if (lower <= rangeSum && rangeSum <= upper)
                    cnt++;
            }
        }
        return cnt;
    }
}
