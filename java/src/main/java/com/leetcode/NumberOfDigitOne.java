package com.leetcode;

/**
 * 233. Number of Digit One
 *
 * Given an integer n, count the total number of digit 1 appearing in all
 * non-negative integers less than or equal to n.
 *
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 *
 * Hint:
 * Beware of overflow.
 *
 * last 1:      x/10   +   minmax(0, x%10-0,    1)
 * 2nd-last 1:  x/100  +   minmax(0, x%100-9,   10)
 * 3rd-last 1:  x/1000 +   minmax(0, x%1000-99, 100)
 * ...
 *              x/10 + minmax(0, x%base-(base/10-1), base/10)
 */
public class NumberOfDigitOne {
    public int countDigitOne(int n) {
        int len = Integer.toString(n).length(), count = 0;
        for (long i = 0, base = 10; i < len; ++i, base *= 10) {
            count += (n / base * (base / 10) + minmax(0, (n % base) - (base / 10 - 1), base / 10));
            System.out.println(count);
        }
        return count;
    }

    private long minmax(long min, long num, long max) {
        return Math.min(max, Math.max(min, num));
    }

    public static void main(String[] args) {
        NumberOfDigitOne s = new NumberOfDigitOne();
        System.out.println(s.countDigitOne(1) == 1);
        System.out.println(s.countDigitOne(8) == 1);
        System.out.println(s.countDigitOne(0) == 0);
        System.out.println(s.countDigitOne(10) == 2);
        System.out.println(s.countDigitOne(11) == 4);
        System.out.println(s.countDigitOne(100) == 21);
    }
}
