package com.leetcode;

/**
 * 357. Count Numbers with Unique Digits
 *
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
 *
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding [11,22,33,44,55,66,77,88,99])
 */
public class CountNumberswithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        int total = 1;
        for (int i = 1; i <= Math.min(10, n); ++i)
            total += C(9, 1) * A(9, i - 1);

        return total;
    }

    /**
     * Choose m out of n, n!/(n-m)!/m!
     */
    private int C(int n, int m) {
        m = ((n - m) < m) ? (n - m) : m;
        int up = 1, down = 1;
        while (m > 0) {
            up *= n--;
            down *= m--;
        }
        return up / down;
    }

    /**
     * Arrange m out of n,  n!/(n-m)!
     */
    private int A(int n, int m) {
        int ret = 1;
        while (m > 0) {
            ret *= n--;
            m--;
        }
        return ret;
    }

    public static void main(String[] args) {
        CountNumberswithUniqueDigits c = new CountNumberswithUniqueDigits();
        System.out.println(c.countNumbersWithUniqueDigits(0));
        System.out.println(c.countNumbersWithUniqueDigits(1));
        System.out.println(c.countNumbersWithUniqueDigits(2));
        System.out.println(c.countNumbersWithUniqueDigits(3));  // 739
        System.out.println(c.countNumbersWithUniqueDigits(4));  // 5275
    }
}
