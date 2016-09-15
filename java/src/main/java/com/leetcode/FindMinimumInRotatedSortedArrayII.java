package com.leetcode;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * 
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 * 
 * NOTE: this question needs more analysis to reach O(logn)
 * */

public class FindMinimumInRotatedSortedArrayII {
	public int findMin_Better(int[] nums) {
        int start = 0, end = nums.length - 1, mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;

			if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                int min = Integer.MAX_VALUE;
                for (int i = start; i <= end; i++)
                    min = Math.min(min, nums[i]);
                return min;
            }

            if (nums[start] == nums[mid]) {
                start = mid;
            } else if (nums[mid] == nums[end]) {
                end = mid;
            } else {
                if (nums[start] < nums[end])
                    return nums[start];

                if (nums[start] < nums[mid])
                    start = mid;
                else
                    end = mid;
            }
        }
        
        return Math.min(nums[start], nums[end]);
    }
}
