package com.leetcode;

/**
 * 47. Permutations II
 * 
 * DESCRIPTION:
 * Given a collection of numbers that might contain duplicates,
 * return all possible unique permutations.
 * 
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 * 
 * Skill:
 * 首先排序
 * 然后再递归的时候 重复数字递归跳过
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(num);
        helper(list, new ArrayList<>(), num, new boolean[num.length]);
        return list;
	}

    private void helper(List<List<Integer>> result, List<Integer> currList,
                        int[] num, boolean[] used){

        if (currList.size() == num.length) {
            result.add((List<Integer>)((ArrayList<Integer>) currList).clone());
            return;
        }

        int prev = Integer.MIN_VALUE;
        for(int i = 0; i < num.length; i++){
            if(!used[i] && num[i] != prev){
                currList.add(num[i]);
                used[i] = true;
                helper(result, currList, num, used);
                currList.remove(currList.size() - 1);
                used[i] = false;
                prev = num[i];
            }
        }
    }
}
