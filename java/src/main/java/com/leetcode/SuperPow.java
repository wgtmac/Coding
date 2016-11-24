package com.leetcode;

/**
 * 372. Super Pow
 *
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is
 * an extremely large positive integer given in the form of an array.
 *
 * Example1:
 * a = 2
 * b = [3]
 * Result: 8
 *
 * Example2:
 * a = 2
 * b = [1,0]
 * Result: 1024
 */
public class SuperPow {
    public int superPow(int a, int[] b) {
        int base = a % NUMBER, ans = 1;
        for (int i = b.length - 1; i >= 0; --i) {
            ans = prune(ans * mod(base, b[i]));
            base = mod(base, 10);
        }
        return ans;
    }

    private static int NUMBER = 1337;

    private int mod(int num, int power) {
        if (power == 0) {
            return 1;
        } else {
            int half = mod(num, power / 2);
            int ret = prune(half * half);
            if (power % 2 != 0) ret = prune(ret * num);
            return ret;
        }
    }

    private int prune(int num) {
        return num > NUMBER ? num % NUMBER : num;
    }
}
