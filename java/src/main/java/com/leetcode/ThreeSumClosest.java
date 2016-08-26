package com.leetcode;

/**
 * 16. 3Sum Closest
 *
 * DESCRIPTION:
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Skill:
 * binary search
 */

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        assert(nums != null && nums.length >= 3);

        Arrays.sort(nums);
        int minSum = nums[0] + nums[1] + nums[2];
        int twoSum, threeSum;

        for (int i = 0; i < nums.length - 2; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                twoSum = nums[i] + nums[j];
                int start = j + 1, end = nums.length - 1, mid;

                while (start + 1 < end) {
                    mid = start + (end - start) / 2;
                    threeSum = twoSum + nums[mid];

                    if (threeSum == target)
                        return target;

                    minSum = upadteMinSum(minSum, threeSum, target);
                    if (threeSum < target)
                        start = mid + 1;
                    else
                        end = mid - 1;
                }

                minSum = upadteMinSum(minSum, twoSum + nums[start], target);
                if (start != end)
                    minSum = upadteMinSum(minSum, twoSum + nums[end], target);

                if (minSum == target)
                    return target;
            }
        }

        return minSum;
    }

    private int upadteMinSum(int minSum, int threeSum, int target) {
        if (Math.abs(minSum - target) > Math.abs(threeSum - target))
            return threeSum;
        else
            return minSum;
    }
}
