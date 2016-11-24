package com.leetcode;

/**
 * 27. Remove Element
 *
 * Given an array and a val, remove all instances of that val in place
 * and return the new length.
 * 
 * The order of elements can be changed.
 * It doesn't matter what you leave beyond the new length.
 * 
 * Skill:
 * 直接移动指针 找到重复的就跳过 不重复就往前写
 */

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int len = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[len++] = nums[i];
            }
        }

        return len;
    }
}
