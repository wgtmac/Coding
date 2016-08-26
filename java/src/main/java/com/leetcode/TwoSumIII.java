package com.leetcode;

/**
 * 170. Two Sum III - Data structure design
 * 
 * DESCRIPTION:
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * 
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * 
 * Skill:
 * 用hashmap存数以及出现次数 防止0+0  2+2之类
 * 
 */
import java.util.HashMap;

public class TwoSumIII {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer> ();
	
	public void add(int number) {
		if (!map.containsKey(number)) {
			map.put(number, 1);
		} else {
		    map.put(number, map.get(number) + 1);
		}
	}

	public boolean find(int value) {
	    for (int i : map.keySet()) {
	    	if (i != value - i) {
	    		if (map.containsKey(value - i)) return true;
	    	} else {
	    		if (map.get(i) > 1) return true;
	    	}
	    }
	    return false;
	}
}
