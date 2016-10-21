package com.leetcode;

/**
 * 90. Subsets II
 * 
 * Given a collection of integers that might contain duplicates, S,
 * return all possible subsets.
 * 
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution keys must not contain duplicate subsets.
 * For example,
 * If S = [1,2,2], a solution is:
 * [
 * 	  [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * 
 * Skill:
 * 先排序
 * 再每个元素选/不选
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 两种递归方法总结
 * 1）
 * func( i ){
 *    add i to result     // 当前i不加入当前path
 *    for (i: i -> n)     // 当前i: i -> n加入当前path
 *        func (i + 1)
 * }
 *
 * A B C =>
 * []  Done
 * A   A(Done)
 *     AB          AB(Done)
 *                 ABC         ABC(Done)
 *     AC          AC(Done)
 * B   B(Done)
 *     BC          BC(Done)
 * C   C(Done)
 * 容易处理前后相等情况
 *
 * 2）
 * func( i ){
 *     func (i + 1)   // 当前i不加入当前path
 *
 *     // add i
 *     func (i + 1)   // 当前i加入当前path
 *     // remove i
 * }
 * A B C =>
 * 0       1       2       3 (i)
 * []      []      []      Done
 *                 C       Done
 *         B       B       Done
 *                 BC      Done
 * A       A       A       Done
 *                 AC      Done
 *         AB      AB      Done
 *                 ABC     Done
 */

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        helper(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void helper(List<List<Integer>> list, ArrayList<Integer> subsets,
                        int[] nums, int index){

        list.add((List<Integer>) subsets.clone());

        for (int i = index; i < nums.length; ++i) {
            if (i > index && nums[i] == nums[i - 1])
                continue;

            subsets.add(nums[i]);
            helper(list, subsets, nums, i + 1);
            subsets.remove(subsets.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsII ss2 = new SubsetsII();
        int[] nums = {1, 2, 2};
        System.out.println(ss2.subsetsWithDup(nums));
    }
}
