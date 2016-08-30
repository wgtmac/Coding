package com.leetcode;

/**
 * 67. Add Binary
 * 
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 *
 */

import java.util.Stack;

public class AddBinary {
    public String addBinary(String a, String b) {
        Stack<Integer> stack = new Stack<>();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0, left, right, sum;
        while (i >= 0 || j >= 0) {
            left = (i >= 0) ? Character.getNumericValue(a.charAt(i--)) : 0;
            right = (j >= 0) ? Character.getNumericValue(b.charAt(j--)) : 0;
            sum = left + right + carry;

            carry = sum / 2;
            sum = sum % 2;

            stack.push(sum);
        }

        if (carry != 0)
            stack.push(1);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        
        return sb.toString();
    }
}
