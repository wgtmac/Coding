package com.leetcode;

import java.util.Arrays;

/**
 * 31. Next Permutation
 *
 * Implement next permutation, which rearranges numbers into the lexicographically
 * next greater permutation of numbers. If such arrangement is not possible, it
 * must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding
 * outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 * Skill:
 *
 * 123|45
 * 123|54 *
 * 12|435
 * 12|453
 * 12|534
 * 12|543 *
 * 1|3245
 * 1|3254
 *
 * 115
 * 151
 * 511
 * 115
 *
 * 1. 从右往左找到第一个数满足 a[i] < a[i+1]，此时i右边所有数递减:
 *     (6) 8 7 4 3 2
 * 2. 再从右往左找到第一个数，满足a[i] < a[j]，交换它两，右边序列仍然不增
 *     6 8 (7) 4 3 2 => (7) 8 (6) 4 3 2
 * 3. 把a[i+1]开始所有数反序 7 (2 3 4 6 8)
 *
 * previous permutation is totally reversed
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int i = nums.length - 2;
        for ( ; i >= 0 && nums[i] >= nums[i + 1] ; i--);

        // if (i == -1) means it is a descending array
        if (i != -1) {
            // there must be some number that is larger than a[i]
            int j;
            for (j = nums.length - 1; j > i && nums[i] >= nums[j]; j--);

            // swap
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }

        // reverse remaining
        for (int j = i + 1; j < nums.length + i - j; j++ ) {
            nums[j] ^= nums[nums.length + i - j];
            nums[nums.length + i - j] ^= nums[j];
            nums[j] ^= nums[nums.length + i - j];
        }
    }

    private void prevPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int i = nums.length - 2;
        for ( ; i >= 0 && nums[i] <= nums[i + 1] ; i--);

        // if (i == -1) means it is a ascending array
        if (i != -1) {
            // there must be some number that is smaller than a[i]
            int j;
            for (j = nums.length - 1; j > i && nums[i] <= nums[j]; j--);

            // swap
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }

        // reverse remaining
        for (int j = i + 1; j < nums.length + i - j; j++ ) {
            nums[j] ^= nums[nums.length + i - j];
            nums[nums.length + i - j] ^= nums[j];
            nums[j] ^= nums[nums.length + i - j];
        }
    }

    public static void main(String[] args) {
        NextPermutation s = new NextPermutation();
        int[] nums = {6, 8, 7, 4, 3, 2};
        s.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        s.prevPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
