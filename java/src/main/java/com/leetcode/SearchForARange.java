package com.leetcode;

/**
 * 34. Search for a Range
 *
 * Given a sorted array of integers,
 * find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * Skill:
 * 二分查找先找最左边 再找最右边
 */
public class SearchForARange {
	public int[] searchRange(int[] A, int target) {
		int[] bound = {-1, -1};
		int start = 0, end = A.length - 1, mid;

		// search for left bound
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (A[mid] < target)
				start = mid;
			else
				end = mid;
		}

		if (A[start] == target)
			bound[0] = start;
		else if (A[end] == target)
			bound[0] = end;
		else
			return bound;

		// search for right bound
		start = 0;
		end = A.length - 1;
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (A[mid] <= target)
				start = mid;
			else
				end = mid;
		}
		if (A[end] == target)
			bound[1] = end;
		else if (A[start] == target)
			bound[1] = start;

		return bound;
	}
}
