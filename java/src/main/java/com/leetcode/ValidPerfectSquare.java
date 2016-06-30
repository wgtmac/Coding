package com.leetcode;

/**
 * 367. Valid Perfect Square
 *
 Given a positive integer num, write a function
 which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:
 Input: 16
 Returns: True

 Example 2:
 Input: 14
 Returns: False
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num <= 0 || num > 2147395600) return false;
        int start = 1, end = 46340;
        int mid = start + (end - start) / 2;
        while (start <= end) {
            mid = start + (end - start) / 2;
            int midSquare = mid * mid;
            if (midSquare == num) {
                break;
            } else if (num < midSquare) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
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
