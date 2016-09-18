package com.leetcode;

/**
 * 171. Excel Sheet Column Number
 * 
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28 
    
 * Skill: 
 * 就是27进制
 * 需要注意  A-Z对应 0～25
 * 
 * A = 0 + 1
 * AA = (0 + 1)*26 + (0 + 1)
 * AAA = (0+1)*26*26 + (0+1)*26 + (0+1)
 * */ 

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
         if (s == null || s.length() == 0) return 0;
         
         int num = 0, count = 0;
         for (int i = s.length() - 1; i >= 0; i--) {
        	 num += Math.pow(26, count) * (s.charAt(i) - 'A' + 1);
        	 count++;
         }
         
         return num;
    }
}
