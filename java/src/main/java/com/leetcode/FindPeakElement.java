package com.leetcode;

/**
 * 162. Find Peak Element
 * 
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Note: Your solution should be in logarithmic complexity.
 * 
 *  
 * Skill:
 * 用二分法
 * 如果mid比两边高，则返回
 * 否则判断mid是递增还是递减
 * 
 * 即使出现波谷也没关系这意味着两边都有波峰
 */
public class FindPeakElement {
    public int findPeakElement(int[] num) {
    	int start = 0, end = num.length - 1, mid, left, right;
    	while (start + 1 < end) {
    		mid = start + (end - start) / 2;

            left = mid > 0 ? num[mid - 1] : Integer.MIN_VALUE;
            right = mid < num.length - 1 ?  num[mid + 1] : Integer.MIN_VALUE;

    		if (left < num[mid] && num[mid] > right) {
    			return mid;
    		} else if (left < num[mid] && num[mid] < right) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	
        left = start > 0 ? num[start - 1] : Integer.MIN_VALUE;
        right = start < num.length - 1 ?  num[start + 1] : Integer.MIN_VALUE;
    	if (left < num[start] && num[start] > right)
    	    return start;
        else
            return end;
    }
}
