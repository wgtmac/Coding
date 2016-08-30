package com.leetcode;

import java.util.Arrays;

/**
 * 43. Multiply Strings
 * 
 * Given two numbers represented as strings,
 * return multiplication of the numbers as a string.
 *
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 * Skill: 
 * 短字符串开始补0
 * 注意进位可能连续
 * 
 * note：
 * 进位可以不急着往前一直处理，因为接下来肯定还要算前一位
 * 只需要开len1+len2的总长度 inplace计算
 * */

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        byte[] result = new byte[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; --i) {
            for (int j = num2.length() - 1; j >= 0; --j) {
                int index = num1.length() - 1 - i + num2.length() - 1 - j;
                result[index] +=
                        Character.getNumericValue(num1.charAt(i)) *
                        Character.getNumericValue(num2.charAt(j));
                if (result[index] >= 10) {
                    result[index + 1] += result[index] / 10;
                    result[index] %= 10;
                }
            }
        }

        int i = result.length - 1;
        for (; i >= 0 && result[i] == 0; --i);
        StringBuilder sb = new StringBuilder ();
        for (; i >= 0; --i)
            sb.append(result[i]);
        return sb.length() == 0 ? "0" : sb.toString();
    }
    
    public static void main (String[] args) {
    	MultiplyStrings t = new MultiplyStrings();
    	System.out.println(t.multiply("1000", "1232"));
        System.out.println(t.multiply("0", "0"));
    }
}
