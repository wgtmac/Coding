package com.leetcode;

/**
 * 152. Maximum Product Subarray
 * 
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest product.
 * 
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * 
 * Skill: DP, 保存每一步的局部最小min和max
 * */

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
		int localMin = 1, localMax = 1, globMax = Integer.MIN_VALUE;
		for (int num : nums) {
			int newlocalMin = Math.min(num, Math.min(num * localMax, num * localMin));
			localMax = Math.max(num, Math.max(num * localMax, num * localMin));
			globMax = Math.max(globMax, localMax);
			localMin = newlocalMin;
		}
		return globMax;
	}
}
