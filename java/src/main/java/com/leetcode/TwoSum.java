package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * 
 * Given an array of integers, return indices of the two numbers such that
 * they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * Skill:
 * 二分
 * 
 * 解法2：排序，连序号一起排 然后两个指针前后移动   O(logn)
 * 解法三：存到hashtable里，直接找剩下一个数存在否  O(n)
 */
public class TwoSum {
    public int[] twoSum_Better(int[] numbers, int target) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < numbers.length; i++) {
    		int x = numbers[i];
    		if (map.containsKey(target - x)) {
    			return new int[] { map.get(target - x) + 1, i + 1 };
    		}
    		map.put(x, i);
    	}
    	throw new IllegalArgumentException("No two sum solution");
    }
    
    public int[] twoSum(int[] numbers, int target) {
    	int[] zeroArr = {0, 0};
    	if (numbers == null){
    		return null;
    	}
    	if(numbers.length <= 1){
    		return zeroArr;
    	}

        for (int i = 0; i < numbers.length; i++){
        	for (int j = i + 1; j < numbers.length; j++){
        		if (numbers[i] + numbers[j] == target){
        			int[] result = {i+1, j+1};
        			return result;
        		}
        	}
        }
        return zeroArr;
    }   
}
