package com.leetcode;

/**
 * 78. Subsets
 * 
 * Given a keys of distinct integers, S, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution keys must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * 
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 *  ]
 *  
 * Skill:
 * 先排序
 * 再每个元素选/不选
 * 递归
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());  // empty list is 1 element
        Arrays.sort(nums);
        helper(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    private void helper(List<List<Integer>> list, ArrayList<Integer> subsets,
                        int[] nums, int index){
        if (index == nums.length) return;

        // add current index
        subsets.add(nums[index]);
        helper(list, subsets, nums, index + 1);
        list.add((List<Integer>)subsets.clone());

        // skip current index
        subsets.remove(subsets.size() - 1);
        helper(list, subsets, nums, index + 1);
    }
}
