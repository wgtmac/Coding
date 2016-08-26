package com.leetcode;

/**
 * 80. Remove Duplicates from Sorted Array II
 *
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 *  
 * Skill:
 * 比较简单
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 3) return nums.length;

        int len = 2;
        for (int i = 2; i < nums.length; ++i) {
            if ((nums[i] != nums[len - 1] || nums[i] != nums[len - 2]))
                nums[len++] = nums[i];
        }

        return len;
    }
}
