package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 409. Longest Palindrome
 *
 * Given a string which consists of lowercase or uppercase letters, find the length
 * of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        int sum = 0;
        boolean hasOdd = false;
        for (int value : map.values()) {
            if (value % 2 == 0) {
                sum += value;
            } else {
                sum += (value - 1);
                hasOdd = true;
            }
        }

        return hasOdd ? sum + 1 : sum;
    }
}
