package com.leetcode;

/**
 * 18. 4Sum
 * 
 * DESCRIPTION:
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution keys must not contain duplicate quadruplets.
 * 
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 *   A solution keys is:
 *    (-1,  0, 0, 1)
 *    (-2, -1, 1, 2)
 *    (-2,  0, 0, 2)
 * 
 * Skill:
 * binary search
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<> ();
        Arrays.sort(nums);

        int remTarget;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                remTarget = target - nums[i] - nums[j];
                List<int[]> twoSumList = twoSum(nums, j + 1, remTarget);

                for (int[] subList : twoSumList) {
                    list.add(Arrays.asList(nums[i], nums[j], subList[0], subList[1]));
                }
            }
        }
        return list;
    }

    private List<int[]> twoSum(int[] nums, int l, int target) {
        List<int[]> list = new ArrayList<>();

        int sum, r = nums.length - 1;
        while (l < r) {
            sum = nums[l] + nums[r];
            if (sum == target){
                list.add(new int[] {nums[l], nums[r]});
                do {
                    ++l;
                } while (l < r && nums[l] == nums[l - 1]);
            } else if (sum < target) {
                ++l;
            } else {
                --r;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        FourSum fs = new FourSum();
        System.out.println(fs.fourSum(new int[] {0, 0, 0, 0}, 0));
    }
}
