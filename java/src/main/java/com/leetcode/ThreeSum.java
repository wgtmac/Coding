package com.leetcode;

/**
 * 170. Two Sum III - Data structure design
 * 
 * DESCRIPTION:
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * 
 * A solution set is:
 *  (-1, 0, 1)
 *  (-1, -1, 2)
 * 
 * Skill:
 * binary search
 */

import java.util.*;
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<> ();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1, k = nums.length - 1; j < k;) {
                if (nums[j] + nums[k] == -nums[i]) {
                    ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    do {
                        j++;
                    } while (j < k && nums[j] == nums[j - 1]);
                } else if (nums[j] + nums[k] < -nums[i]) {
                    j++;
                } else{
                    k--;
                }
            }
        }

        return ret;
    }
}
