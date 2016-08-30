package com.leetcode;

/**
 * 65. Valid Number
 * 
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. 
 * You should gather all requirements up front before implementing one.
 * 
 * Skill:
 * 先trim
 * 判断第一个是不是+-
 * 然后剩下的数字，只能出现一次. e 
 * 如果出现+-,只能出现在e后面
 * 如果出现. 不能前面检测到e
 * 如果出现e 后面不能没有数字 前面也不能没有数字
 * 
 * 或者 先检查+-，然后检查数字 再检查. 然后检查数字 然后检查e 然后检查+- 再检查数字
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        return s.trim().matches("^([+-]?)((\\d*\\.\\d+)|\\d+\\.?)(e([+-]?)\\d+)?$");
    }

    public boolean isNumber_regular(String s) {
        if (s == null || s.isEmpty()) return false;
        int i = checkSpace(s, 0);    // ' ' is expected
        i = checkSign(s, i);     // + / - can be expected

        // digit is expected
        i = checkDigit(s, i, true);
        if (i == -1) return false;

        // 'e' may be expected
        if (i < s.length() && s.charAt(i) == 'e') {
            ++i;
            // + / - can be expected
            i = checkSign(s, i);
            // digit is expected
            i = checkDigit(s, i, false);
            if (i == -1) return false;
        }

        i = checkSpace(s, i);    // ' ' may be expected
        return i == s.length();
    }

    private int checkSpace(String s, int i) {
        for (; i < s.length() && Character.isSpaceChar(s.charAt(i)); ++i);
        return i;
    }

    private int checkSign(String s, int i) {
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
            ++i;
        return i;
    }

    private int checkDigit(String s, int i, boolean checkWithDot) {
        boolean foundDigit = false;
        for (; i < s.length() && Character.isDigit(s.charAt(i)); ++i)
            foundDigit = true;

        if (checkWithDot) {
            if (i < s.length() && s.charAt(i) == '.')
                ++i;

            for (; i < s.length() && Character.isDigit(s.charAt(i)); ++i)
                foundDigit = true;
        }

        return foundDigit ? i : -1;
    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        System.out.println(vn.isNumber_regular("2e0"));
    }
}
