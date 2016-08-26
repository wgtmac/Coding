package com.leetcode;

/**
 * 167. Two Sum II - Input array is sorted
 * 
 * DESCRIPTION:
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 * Skill:
 * 二分
 * 
 * 解法2：排序，连序号一起排 然后两个指针前后移动   O(logn)
 * 解法三：存到hashtable里，直接找剩下一个数存在否  O(n)
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[] {0, 0};
        for (int i = 0, j = numbers.length - 1; i < j; ) {
        	if (numbers[i] + numbers[j] == target) {
        		return new int[] {i + 1, j + 1};
        	} else if (numbers[i] + numbers[j] < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
        
        return new int[] {0, 0};
    }
}
