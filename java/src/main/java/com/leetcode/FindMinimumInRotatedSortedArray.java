package com.leetcode;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * 
 * 二分查找
 * 一边递增，另一边断开
 * 每次找到纯递增那边，更新最小值，另一边下次处理
 * */

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = nums.length - 1, mid;
        while (start + 1 < end){
            if (nums[start] < nums[end])
                return nums[start];

            mid = start + (end - start) / 2;
            if (nums[start] < nums[mid])
                start = mid;
            else
                end = mid;
        }
        
        return Math.min(nums[start], nums[end]);
    }
}
