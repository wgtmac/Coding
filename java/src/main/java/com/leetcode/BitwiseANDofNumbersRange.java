package com.leetcode;

/**
 * 201. Bitwise AND of Numbers Range
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 *
 * For example, given the range [5, 7], you should return 4.
 */
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        for (int i = 0xFFFFFFFF; m != n && i != 0; i <<= 1) {
            m &= i;
            n &= i;
        }
        return m;
    }
}
