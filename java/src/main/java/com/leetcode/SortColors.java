package com.leetcode;

/**
 * 75. Sort Colors
 * 
 * Given an array with n objects colored red, white or blue,
 * sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 *
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 *
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, 
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 */

public class SortColors {
    public void sortColors(int[] nums) {
        int lHeadNonZero = 0, rHeadNonTwo = nums.length - 1, i = 0;

        while (i <= rHeadNonTwo) {
            if (nums[i] == 0) {
                swap(nums, lHeadNonZero, i);
                if (i == lHeadNonZero++)
                    ++i;
            } else if (nums[i] == 2) {
                swap(nums, rHeadNonTwo, i);
                rHeadNonTwo--;
            } else {   // nums[i] == 1
                ++i;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
