package com.leetcode;

import java.util.TreeMap;

/**
 * 220. Contains Duplicate III
 *
 * Given an array of integers, find out whether there are two distinct indices i
 * and j in the array such that the difference between nums[i] and nums[j] is at
 * most t and the difference between i and j is at most k.
*/

public class ContainsDuplicatesIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	if (t < 0) return false;

        TreeMap<Long, Integer> map = new TreeMap<>();

        for (int i = 0; i < nums.length; ++i) {
        	if (map.containsKey((long)nums[i]) && Math.abs(map.get((long)nums[i]) - i) <= k) {
        		return true;
        	}

        	Long key = map.lowerKey((long)nums[i]);
        	while (key != null && Math.abs(key - nums[i]) <= t) {
        		if (Math.abs(map.get(key) - i) <= k) return true;
        		else key = map.lowerKey(key);
        	}

        	key = map.higherKey((long)nums[i]);
        	while (key != null && Math.abs(key - nums[i]) <= t) {
        		if (Math.abs(map.get(key) - i) <= k) return true;
        		else key = map.higherKey(key);
        	}

        	map.put((long)nums[i], i);
        }

        return false;
    }
}