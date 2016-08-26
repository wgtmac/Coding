package com.leetcode;

/**
 * 3. Longest Substring Without Repeating Characters
 * 
 * DESCRIPTION:
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * 
 * Skill:
 * 用hashmap记录前面出现过的字符的最近位置
 * 
 */

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        int len = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) + len >= i){
                len = i - map.get(ch);
                map.put(ch, i);
            } else {
                len++;
                map.put(ch, i);
            }
            max = Math.max(max, len);
        }
        
        return max;
    }
}
