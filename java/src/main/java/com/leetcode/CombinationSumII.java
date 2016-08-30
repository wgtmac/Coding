package com.leetcode;

/**
 * 40. Combination Sum II
 * 
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 *
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 * A solution set is: 
 * [1, 7] 
 * [1, 2, 5] 
 * [2, 6] 
 * [1, 1, 6] 
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            // here should be i + 1 since each number can only be used once
            helper(candidates, i + 1, target - candidates[i], currList, result);
            currList.remove(currList.size() - 1);

            prev = candidates[i];
        }
    }
}
