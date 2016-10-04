package com.leetcode;

/**
 * 268. Missing Number
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find
 * the one that is missing from the array.
 *
 * For example,
 * Given nums = [0, 1, 3] return 2.
 *
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        long sum = 0;
        long n = Integer.MIN_VALUE;
        boolean zeroExist = false;

        for (int num : nums) {
            n = Math.max(n, num);
            sum += num;
            if (num == 0) zeroExist = true;
        }

        int missing = (int)((1 + n) * n / 2 - sum);

        if (missing == 0 && zeroExist) {
            return (int)n + 1;
        } else {
            return missing;
        }
    }
}
