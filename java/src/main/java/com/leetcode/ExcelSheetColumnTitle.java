package com.leetcode;

/**
 * 168. Excel Sheet Column Title
 * 
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 *  For example:
 *  1 -> A
 *  2 -> B
 *  3 -> C
 *  ...
 *  26 -> Z
 *  27 -> AA
 *  28 -> AB
 *
 * Skill: 
 * 就是26进制
 *
 *   yx = (x - A + 1) + (y - A + 1) * 26
 *
 *
 * 需要注意  A-Z对应 0～25
 * A = 1
 * AA = (0 + 1)*26 + (0 + 1) = 26 * 1 + 1
 * AZ = 26 * 1 + 26
 * AAA = (0+1)*26*26 + (0+1)*26 + (0+1)  = 26 * 26 * 1 + 26 * 1 + 1
 * 
 * 获得个位数 (n-1)%26+1
 * */ 

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        String ret = "";
        while (n > 26) {
        	ret = (char)('A' + getDigit(n)) + ret;
        	n = (n - (getDigit(n) + 1)) / 26;
        }
        if (n > 0) ret = (char)('A' + getDigit(n)) + ret;
        return ret;
    }

    /**
     * Return last digit as A=0, B=1, ..., Z=25
     * */
    private int getDigit(int n) {
        return (n - 1) % 26;
    }
}
