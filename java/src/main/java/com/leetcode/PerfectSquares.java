package com.leetcode;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 279. Perfect Squares
 *
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13,
 * return 2 because 13 = 4 + 9.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] f = new int[n + 1];

        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 1, j = i * i; j <= n; ++i, j = i * i) {
            set.add(j);
        }

        for (int i = 1; i <= n; ++i) {
            if (set.contains(i)) {
                f[i] = 1;
            } else {
                f[i] = Integer.MAX_VALUE;

                for (int j : set) {
                    if (j > i) break;
                    f[i] = Math.min(f[i], f[j] + f[i - j]);
                }
            }
        }

        return f[n];
    }
}
