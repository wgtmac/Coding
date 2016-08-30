package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 *
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination
 * should be a unique set of numbers.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        helper(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void helper (int currNum, int target, int k, List<Integer> currList,
                         List<List<Integer>> result) {
        if (currList.size() == k) {
            if (target == 0)
                result.add((List<Integer>)((ArrayList<Integer>) currList).clone());
            return;
        } else if (currNum > 9 && currNum > target) {
            return;
        }

        // current number is skipped
        helper(currNum + 1, target, k, currList, result);

        // current number is used
        currList.add(currNum);
        helper(currNum + 1, target - currNum, k, currList, result);
        currList.remove(currList.size() - 1);
    }
}
