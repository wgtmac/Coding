package com.leetcode;

/**
 * 39. Combination Sum
 * 
 * Given a keys of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 *
 * The solution keys must not contain duplicate combinations.
 * For example, given candidate keys 2,3,6,7 and target 7,
 * A solution keys is:
 * [7] 
 * [2, 2, 3] 
 * 
 * Skill:
 * 递归计算
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<> ();
    	if (candidates == null || candidates.length == 0) return result;
    	Arrays.sort(candidates);
    	helper(candidates, 0, target, new ArrayList<>(), result);
    	return result;
    }


    void helper(int[] candidates, int startIdx, int target,
                List<Integer> currList, List<List<Integer>> result) {
        if (target == 0) {
            result.add((List<Integer>)((ArrayList<Integer>) currList).clone());
            return;
        }

        // there might be some duplicate numbers
        int prev = Integer.MIN_VALUE;   // assumption: all numbers are positive
        for (int i = startIdx; i < candidates.length; i++) {
            if (candidates[i] > target)
                break;

            if (prev == candidates[i])
                continue;

            currList.add(candidates[i]);
            helper(candidates, i, target - candidates[i], currList, result);
            currList.remove(currList.size() - 1);

            prev = candidates[i];
        }
    }
}
