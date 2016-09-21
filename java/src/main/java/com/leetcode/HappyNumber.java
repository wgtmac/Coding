package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy Number
 *
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with
 * any positive integer, replace the number by the sum of the squares of its
 * digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. Those numbers for
 * which this process ends in 1 are happy numbers.
 *
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int prev;
        while (n > 1) {
            if (set.contains(n)) break;
            set.add(n);
            prev = n;
            n = 0;
            while (prev != 0) {
                n += (prev % 10) * (prev % 10);
                prev /= 10;
            }
        }
        return n == 1;
    }
}
