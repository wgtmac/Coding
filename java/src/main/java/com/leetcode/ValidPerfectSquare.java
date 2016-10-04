package com.leetcode;

/**
 * 367. Valid Perfect Square
 *
 * Given a positive integer num, write a function which returns True if num is a
 * perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 * Input: 16
 * Returns: True
 *
 * Example 2:
 * Input: 14
 * Returns: False
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;

        long start = 1, end = num / 2 + 1;
        long mid, midSquare;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            midSquare = mid * mid;
            if (midSquare == num) return true;
            if (midSquare < num) start = mid;
            else end = mid;
        }
        mid = start + (end - start) / 2;
        return mid * mid == num;
    }

    public static void main(String[] args) {
        ValidPerfectSquare vps = new ValidPerfectSquare();
        System.out.println(vps.isPerfectSquare(1));
        System.out.println(vps.isPerfectSquare(16));
        System.out.println(vps.isPerfectSquare(4));
        System.out.println(vps.isPerfectSquare(2147395600));
        System.out.println(vps.isPerfectSquare(3));
        System.out.println(vps.isPerfectSquare(13));
        System.out.println(vps.isPerfectSquare(333));
        System.out.println(vps.isPerfectSquare(2147395605));

    }
}
