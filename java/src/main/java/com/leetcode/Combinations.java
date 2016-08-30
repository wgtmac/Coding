package com.leetcode;

/**
 * 77. Combinations
 * 
 * Given two integers n and k,
 * return all possible combinations of k numbers out of 1 ... n.
 * 
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<> ();
    	if (n < 1 || k > n || k < 1) return result;
    	
    	int[] candidates = new int[n];
    	for (int i = 0; i < n; i++)
    		candidates[i] = i + 1;

    	helper(candidates, 0, k, new ArrayList<>(), result);
    	return result;
    }
    
	private void helper (int[] candidates, int index, int k,
						List<Integer> currList, List<List<Integer>> result) {
		if (currList.size() == k) {
			result.add((List<Integer>)((ArrayList<Integer>) currList).clone());
			return;
		} else if (index == candidates.length) {
			return;
		}

		// current number is skipped
		helper(candidates, index + 1, k, currList, result);

		// current number is used
		currList.add(candidates[index]);
		helper(candidates, index + 1, k, currList, result);
		currList.remove(currList.size() - 1);
	}
}
