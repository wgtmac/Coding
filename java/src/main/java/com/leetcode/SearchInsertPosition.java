package com.leetcode;

/**
 * 35. Search Insert Position
 *
 * Given a sorted array and a target value,
 * return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 * Skill:
 * 二分查找
 */
public class SearchInsertPosition {
	public int searchInsert(int[] A, int target){
		// normal binary search
		int start = 0, end = A.length - 1, mid;
		while (start + 1 < end){
			mid = start + (end - start)/2;
			if(A[mid] == target) return mid;
			else if(A[mid] < target) start = mid;
			else end = mid;
		}

		// can't find -- comparision
		if(target <= A[start]) return start;
		else if(A[start] < target && target <= A[end]) return end;
		else return end + 1;
	}
}
