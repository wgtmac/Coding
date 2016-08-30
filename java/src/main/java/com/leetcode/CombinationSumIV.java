package com.leetcode;

import java.util.Arrays;

/**
 * 377. Combination Sum IV
 *
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 * Hint:
 * DP, compute how many ways to get number i increasingly.
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] waysToGetSum = new int[target + 1];
        waysToGetSum[0] = 1;

        Arrays.sort(nums);
        for (int i = 1; i <= target; ++i)
            for (int j = 0; j < nums.length && nums[j] <= i; ++j)
                waysToGetSum[i] += waysToGetSum[i - nums[j]];

        return waysToGetSum[target];
    }

    public static void main(String[] args) {
        CombinationSumIV cs = new CombinationSumIV();
        System.out.println(cs.combinationSum4(new int[] {1, 2, 3}, 4));
    }
}
