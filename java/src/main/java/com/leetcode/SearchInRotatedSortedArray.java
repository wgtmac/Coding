package com.leetcode;

/**
 * 33. Search in Rotated Sorted Array
 * 
 * DESCRIPTION:
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index,
 * otherwise return -1.
 * You may assume no duplicate exists in the array.
 * 
 * Skill:
 * 二分查找
 * 先找到递增那一段
 * 
 * TRICK: 最小一定在错开的那一个区间
 */
public class SearchInRotatedSortedArray {
    public int search(int[] A, int target) {
        if ( A == null || A.length == 0 )
            return  -1;

        int start = 0, end = A.length - 1, mid;
        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (A[mid] == target)
                return mid;
            else if (A[start] < A[mid]) {
                if (target < A[mid] && A[start] <= target)
                	end = mid;
                else
                	start = mid;
            } else {
                if (A[mid] < target && target <= A[end])
                	start = mid;
                else
                	end = mid;
            }
        }        

        if (A[start] == target) return  start;
        if (A[end] == target)   return  end;
        return -1;
    }
}
