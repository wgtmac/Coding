package com.leetcode;

/**
 * 46. Permutations
 * 
 * Given a collection of numbers, return all possible permutations.
 * 
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * Skill:
 * 递归尝试 用数组or hashtable记录哪些数已经被用
 */

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public List<List<Integer>> permute(int[] num){
		List<List<Integer>> list = new ArrayList<>();
		helper(list, new ArrayList<>(), num, new boolean[num.length]);
		return list;
	}
	
	private void helper(List<List<Integer>> result, List<Integer> currList,
                        int[] num, boolean[] used){

	    if (currList.size() == num.length) {
            result.add((List<Integer>)((ArrayList<Integer>) currList).clone());
            return;
        }

        for(int i = 0; i < num.length; i++){
            if(!used[i]){
                currList.add(num[i]);
                used[i] = true;
                helper(result, currList, num, used);
                currList.remove(currList.size() - 1);
                used[i] = false;
            }
        }
	}
}
