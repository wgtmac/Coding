package com.leetcode;

/**
 * 50. Pow(x, n)
 * 
 * Implement pow(x, n).
 * 
 * Skill:
 * 需要讨论n是否小于0
 * 需要讨论n的绝对值是否是奇数
 */

public class Powxn {
    public double myPow(double x, int n) {
        switch (n) {
            case 0: return 1;
            case 1: return x;
            case -1: return 1 / x;
            default:
                int absN = Math.abs(n);
                if (n % 2 == 0) {
                    double tmp = myPow(x, n / 2);
                    return tmp * tmp;
                } else {
                    double tmp = myPow(x, (n - 1) / 2);
                    return tmp * tmp * x;
                }
        }
    }
}
