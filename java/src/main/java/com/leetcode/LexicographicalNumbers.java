package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 386. Lexicographical Numbers
 *
 * Given an integer n, return 1 - n in lexicographical order.
 *
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 *
 * Please optimize your algorithm to use less time and space.
 * The input size may be as large as 5,000,000.
 */
public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        helper(0, n, ans);
        return ans;
    }

    private void helper(int num, int threshold, List<Integer> list) {
        num *= 10;
        for (int i = 0; i <= 9; ++i) {
            int cand = num + i;
            if (0 < cand && cand <= threshold) {
                list.add(cand);
                helper(cand, threshold, list);
            }
        }
    }
}
