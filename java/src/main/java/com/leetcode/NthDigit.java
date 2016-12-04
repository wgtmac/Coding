package com.leetcode;

/**
 * 400. Nth Digit
 *
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 *
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).
 *
 * Example 1:
 *
 * Input:
 * 3
 *
 * Output:
 * 3
 *
 * Example 2:
 *
 * Input:
 * 11
 *
 * Output:
 * 0
 *
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0,
 * which is part of the number 10.
 */
public class NthDigit {
    public int findNthDigit(int n) {
        long base = 9, numDigits = 1;
        while (n > base * numDigits) {
            n -= base * numDigits++;
            base *= 10;
        }

        String num = Integer.toString((int)(base / 9 + (n - 1) / numDigits));
        return num.charAt((int)((n - 1) % numDigits)) - '0';
    }
}
