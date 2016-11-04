package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 254. Factor Combinations
 *
 * Numbers can be regarded as product of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 *
 * Write a function that takes an integer n and return all possible combinations
 * of its factors.
 *
 * Note:
 *   1. You may assume that n is always positive.
 *   2. Factors should be greater than 1 and less than n.
 *
 * Examples:
 *   input: 1
 *   output: []
 *
 *   input: 37
 *   output: []
 *
 *   input: 12
 *   output:
 *   [
 *     [2, 6],
 *     [2, 2, 3],
 *     [3, 4]
 *   ]
 *
 *   input: 32
 *   output:
 *   [
 *     [2, 16],
 *     [2, 2, 8],
 *     [2, 2, 2, 4],
 *     [2, 2, 2, 2, 2],
 *     [2, 4, 4],
 *     [4, 8]
 *   ]
 */
public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        Map<Integer, List<List<Integer>>> cache = new HashMap<>();
        cache.put(1, new ArrayList<>());
        cache.put(n, new ArrayList<>());

        helper(1, n, new ArrayList<>(), cache);
        return cache.get(n);
    }

    /**
     * @param lastFactor: last factor has been processed (in non-descending order)
     * @param num: remaining number to find factors
     * @param list: accumulated factors
     * @param cache: store all processed result
     */
    private void helper(int lastFactor, int num, List<Integer> list, Map<Integer, List<List<Integer>>> cache) {
        if (!cache.containsKey(num)) {
            cache.put(num, new ArrayList<>());

            for (int factor = lastFactor; factor < num; ++factor) {
                if (num % factor == 0) {
                    list.add(factor);
                    helper(factor, num / factor, list, cache);
                    list.remove(list.size() - 1);

                    for (List<Integer> lists : cache.get(num / factor)) {
                        List<Integer>  = new ArrayList<>();
                    }
                    subList.add(factor);
                    subList.addAll();
                    cache.get(num).add();
                }
            }
        }
    }
}
