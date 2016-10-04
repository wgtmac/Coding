package com.leetcode;

/**
 * 326. Power of Three
 *
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Follow up:
 * Could you do it without using any loop / recursion?
 */
public class PowerofThree {
    public boolean isPowerOfThree(int n) {
        return n > 0 && Math.pow(3, 21) % n == 0;
    }
}
