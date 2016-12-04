package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 *
 * Given a positive integer n and you can do operations as follow:
 *
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * What is the minimum number of replacements needed for n to become 1?
 *
 * Example 1:
 *   Input:
 *   8
 *   Output:
 *   3
 *   Explanation:
 *   8 -> 4 -> 2 -> 1
 *
 * Example 2:
 *   Input:
 *   7
 *   Output:
 *   4
 *   Explanation:
 *   7 -> 8 -> 4 -> 2 -> 1
 *   or
 *   7 -> 6 -> 3 -> 2 -> 1
 */
public class IntegerReplacement {
    public static void main(String[] args) {
        IntegerReplacement i = new IntegerReplacement();
        System.out.println(i.integerReplacement(100));
    }

    public int integerReplacement(int n) {
        Map<Long, Integer> cache = new HashMap<>();
        cache.put(1l, 0);
        return helper(n, cache);
    }

    private int helper(long num, Map<Long, Integer> cache) {
        if (!cache.containsKey(num)) {
            if (num % 2 == 0) {
                cache.put(num, 1 + helper(num / 2, cache));
            } else {
                cache.put(num, 1 + Math.min(helper(num - 1, cache), helper(num + 1, cache)));
            }
        }
        return cache.get(num);
    }

    public int integerReplacement_MLE(int n) {
        int[] f = new int[n % 2 == 0 ? n + 1 : n + 2];
        int odd, even;
        for (int i = 1; i <= ((f.length - 1) >> 1); ++i) {
            even = i << 1;
            odd = even - 1;
            f[even] = f[i] + 1;
            if (odd != 1) {
                f[odd] = Math.min(f[even], f[odd - 1]) + 1;
            }
        }
        return f[n];
    }
}
