package com.leetcode;

/**
 * 66. Plus One
 * 
 * Given a non-negative number represented as an array of digits,
 * plus one to the number.
 * The digits are stored such that the most significant digit is
 * at the head of the list.
 *
 */

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        byte carry = 1;
        for (int i = digits.length - 1; i >= 0 && carry == 1; i--) {
            digits[i] += carry;
            if (digits[i] == 10)
                digits[i] = 0;
            else
                carry = 0;
        }
        
        if (carry == 1) {
            int[] num = new int[digits.length + 1];
            num[0] = 1;
            return num;
        } else {
            return digits;
        }
    }
}
