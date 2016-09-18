package com.leetcode;

/**
 * 166. Fraction to Recurring Decimal
 * 
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format. If the fractional part is repeating,
 * enclose the repeating part in parentheses.
 *
 * For example,
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 *
 * Hint:
 * 先算出整数部分
 * 每次用剩余部分去除，得到重复的余数就对了
 * 余数存在hashmap的key里 value为当前的小数位index
 * 注意，这里用了long，遇到Integer.MIN_VALUE需要特殊处理
 */
import java.util.*;

public class FractiontoRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        long upper = numerator, lower = denominator;
        byte sign = (byte) ((upper / Math.abs(upper)) * (lower / Math.abs(lower)));
        upper = Math.abs((long)numerator);
        lower = Math.abs((long)denominator);

        String integral = Long.toString(upper / lower);
        long rem = upper % lower;
        if (rem == 0)
            return sign < 0 ? '-' + integral : integral;

        Map<Long, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        long shiftedRem;
        while (rem != 0 && !map.containsKey(rem)) {
            shiftedRem = rem * 10;
            map.put(rem, list.size());
            list.add((int) (shiftedRem / lower));
            rem = (shiftedRem % lower);
        }

        StringBuilder decimal = new StringBuilder();
        if (rem != 0) {
            int repeatIndex = map.get(rem), i = 0;

            while (i < repeatIndex)
                decimal.append(list.get(i++));
            decimal.append('(');
            while (i < list.size())
                decimal.append(list.get(i++));
            decimal.append(')');
        } else {
            for (int digit : list)
                decimal.append(digit);
        }

        String resultNum = integral + '.' + decimal.toString();
        return sign < 0 ? '-' +  resultNum : resultNum;
    }
    
    public static void main (String[] args) {
        FractiontoRecurringDecimal f = new FractiontoRecurringDecimal();
    	System.out.println(f.fractionToDecimal(-1 , -2147483648));
    }
}
