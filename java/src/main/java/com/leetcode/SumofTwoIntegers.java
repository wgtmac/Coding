package com.leetcode;

/**
 * 371. Sum of Two Integers
 *
 Calculate the sum of two integers a and b,
 but you are not allowed to use the operator + and -.

 Example:
 Given a = 1 and b = 2, return 3.
 */
public class SumofTwoIntegers {
    public int getSum(int a, int b) {
        int carry = a & b, sum = a ^ b;

        while (carry != 0) {
            a = sum;
            b = carry << 1;
            sum = a ^ b;
            carry = a & b;
        }

        return sum;
    }

    public static void main(String[] args) {
        SumofTwoIntegers sti = new SumofTwoIntegers();
        System.out.println(sti.getSum(1, 2));
        System.out.println(sti.getSum(17, -11));
        System.out.println(sti.getSum(-4, -5));
    }
}
