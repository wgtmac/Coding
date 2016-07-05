package com.leetcode;

/**
 * 343. Integer Break
 *
 Given a positive integer n, break it into the sum of at least two positive integers
 and maximize the product of those integers. Return the maximum product you can get.

 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class IntegerBreak {
    /**
     * x = 3 * a + 2 * b = (1 + 2) * a + 2 * b = 1 * a + (a + b) * 2
     * e.g. 6 = 2 + 2 + 2 = 3 + 3 => 2 * 2 * 2 < 3 * 3
     * 8 = 2 + 2 + 2 + 2 = 3 + 3 + 2 => 2 * 2 * 2 * 2 < 3 * 3 * 2
     * */
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int f3 = n / 3, f2, rem = n - f3 * 3;
        if (rem % 2 == 0) {
            f2 = rem / 2;
        } else {
            f3--;
            f2 = (3 + rem) / 2;
        }

        return (int) (Math.pow(3, f3) * Math.pow(2, f2));
    }
}
