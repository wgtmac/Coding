package com.leetcode;

import java.util.Random;

/**
 * 384. Shuffle an Array
 *
 Shuffle a set of numbers without duplicates.

 Example:

 // Init an array with set 1, 2, and 3.
 int[] nums = {1,2,3};
 Solution solution = new Solution(nums);

 // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 solution.shuffle();

 // Resets the array back to its original configuration [1,2,3].
 solution.reset();

 // Returns the random shuffling of array [1,2,3].
 solution.shuffle();
 */
public class ShuffleAnArray {
    public class Solution {
        int[] oriNums;
        Random rnd;

        public Solution(int[] nums) {
            oriNums = new int[nums.length];
            System.arraycopy(nums, 0, oriNums, 0, nums.length);

            rnd = new Random(System.currentTimeMillis());
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            int[] copy = new int[oriNums.length];
            System.arraycopy(oriNums, 0, copy, 0, oriNums.length);
            return copy;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] nums = reset();
            for (int i = 1; i < nums.length; ++i) {
                int j = rnd.nextInt(i + 1);
                if (i != j) {
                    nums[i] ^= nums[j];
                    nums[j] ^= nums[i];
                    nums[i] ^= nums[j];
                }
            }
            return nums;
        }
    }
}
