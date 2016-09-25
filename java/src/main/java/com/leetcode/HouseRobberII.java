package com.leetcode;

/**
 * 213. House Robber II
 *
 * After robbing those houses on that street, the thief has found himself a new
 * place for his thievery so that he will not get too much attention. This time,
 * all houses at this place are arranged in a circle. That means the first house
 * is the neighbor of the last one. Meanwhile, the security system for these
 * houses remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length <= 2)
            return Math.max(nums[0], nums[nums.length - 1]);

        // if first one can be robbed, last one cannot be robbed
        int left2 = 0, left1 = 0, curr;
        for (int i = 0; i < nums.length - 1; ++i) {
            curr = Math.max(left2 + nums[i], left1);
            left2 = left1;
            left1 = curr;
        }

        int maxRobbed = Math.max(left1, left2);

        // if first one is skipped, last one can be robbed
        left2 = left1 = 0;
        for (int i = 1; i < nums.length; ++i) {
            curr = Math.max(left2 + nums[i], left1);
            left2 = left1;
            left1 = curr;
        }

        return Math.max(maxRobbed, Math.max(left1, left2));
    }
}
