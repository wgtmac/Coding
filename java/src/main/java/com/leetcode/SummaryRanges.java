package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0)
        	return list;

        int prev = nums[0], start = nums[0];
        for (int i = 1; i < nums.length; ++i) {
        	if (prev + 1 != nums[i]) {
        		if (prev == start)
        			list.add("" + start);
        		else
        			list.add(start + "->" + prev);
        		start = nums[i];
        	}
        	prev = nums[i];
        }
        if (prev == start) 
        	list.add("" + start);
        else 
        	list.add(start + "->" + prev);

        return list;
    }
}