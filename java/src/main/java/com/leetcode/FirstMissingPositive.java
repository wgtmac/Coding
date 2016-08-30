package com.leetcode;

/**
 * 41. First Missing Positive
 * 
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * Hint:
 * 因为数组长度为len，则最多为1~len个正数
 * -1存就是考虑上面这种极端情况
 * 
 * 这种要把当前位置的数放到其他地方的题，进行交换后，当前的i不动 直到满足条件，所以不需要新的循环
 */

public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

		for (int i = 0, tmp; i < nums.length; ) {
			// make A[k - 1] = k
			if (nums[i] > 0 && nums[i] - 1 < nums.length && nums[nums[i] - 1] != nums[i]) {
				tmp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = tmp;
			} else {
				i++;
			}
		}
		
		for (int i = 0; i < nums.length; i++)
			if (nums[i] != i + 1) return i + 1;

		return nums.length + 1;
	}
}
