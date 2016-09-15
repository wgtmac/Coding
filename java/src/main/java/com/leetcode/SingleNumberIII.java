package com.leetcode;

/**
 * 260. Single Number III
 *
 * Given an array of numbers nums, in which exactly two elements appear
 * only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 *
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 *
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant space complexity?
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int all = 0, dummy = 1;
        for (int num : nums)
            all ^= num;

        // find any non-zero bit
        while ((dummy & all) == 0) {
            dummy <<= 1;
        }

        int[] ret = {0, 0};
        for (int num : nums) {
            if ((num & dummy) == 0)
                ret[0] ^= num;
            else
                ret[1] ^= num;
        }

        return ret;
    }
}
