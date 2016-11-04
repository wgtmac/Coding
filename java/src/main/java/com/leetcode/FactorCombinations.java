package com.leetcode;

import java.util.*;

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
    public static void main(String[] args) {
        FactorCombinations f = new FactorCombinations();
        System.out.println(f.getFactors(32));
        System.out.println(f.getFactors(12));
        System.out.println(f.getFactors(16));
        System.out.println(f.getFactors(37));
    }

    public List<List<Integer>> getFactors(int n) {
        Map<Integer, List<List<Integer>>> cache = new HashMap<>();
        helper(2, n, cache, n);
        return cache.get(n);
    }

    /**
     * @param lastFactor: last factor has been processed (in non-descending order)
     * @param remNum: remaining number to find factors
     * @param cache: store all processed result
     */
    private void helper(int lastFactor, int remNum, Map<Integer, List<List<Integer>>> cache, int n) {
        if (!cache.containsKey(remNum)) {
            cache.put(remNum, new ArrayList<>());

            for (int factor = lastFactor; factor * factor <= remNum; ++factor) {
                if (remNum % factor == 0) {
                    helper(factor, remNum / factor, cache, n);

                    for (List<Integer> tailList : cache.get(remNum / factor)) {
                        if (!tailList.isEmpty() && tailList.get(0) < factor)
                            continue;

                        List<Integer> currList = new ArrayList<>();
                        currList.add(factor);
                        currList.addAll(tailList);
                        cache.get(remNum).add(currList);
                    }
                }
            }

            // process itself
            if (remNum != n)
                cache.get(remNum).add(new ArrayList<Integer>() {{ add(remNum);}});
        }
    }
}
