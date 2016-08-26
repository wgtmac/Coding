package com.leetcode;

/**
 * 81. Search in Rotated Sorted Array II
 *
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 *  
 * Skill:
 * 需要研究O(logn)算法
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        if (nums == null) return false;
        
        int start = 0, end = nums.length - 1, mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;

            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target < nums[mid])
                    end = mid;
                else
                    start = mid;
            } else if (nums[mid] < nums[end]) {
                if (nums[mid] < target && target <= nums[end])
                    start = mid;
                else
                    end = mid;
            } else {
                if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                    for (int i = start; i <= end; ++i)
                        if (nums[i] == target) return true;
                    return false;
                } else if (nums[start] == nums[mid])
                    start = mid;
                else //if (nums[mid] == nums[end])
                    end = mid;
            }
        }

        if (nums[start] == target || nums[end] == target)
            return true;
        return false;
    }
}
