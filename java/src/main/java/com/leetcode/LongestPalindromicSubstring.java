package com.leetcode;

/**
 * 5. Longest Palindromic Substring
 * 
 * DESCRIPTION:
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * 
 * Skill:
 * 循环暴力寻找, 技巧 从中间往两边扩散
 * 
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String ret = "";
        if (s == null || s.length() == 0) return ret;
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            for (int side = 0; (i - side >=0) && (i + side < len); side++) {
                if (s.charAt(i - side) == s.charAt(i + side)) {
                    if (2 * side + 1 > ret.length())
                        ret = s.substring(i - side, i + side + 1);
                }
                else break;
            }
            
            for (int side = 0; (i - side >= 0) && (i + 1 + side < len); side++) {
                if (s.charAt(i - side) == s.charAt(i + 1 + side)) {
                    if (2 * side + 2 > ret.length()) 
                        ret = s.substring(i - side, i + side + 2);
                }
                else break;
            }
        }
        
        return ret;
    }
}
