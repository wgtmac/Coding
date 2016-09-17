package com.leetcode;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * 
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 * 
 * Skill:
 * 
 * start 记录当前最长string的头
 * hashmap存最近两个char的最后index
 */
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostTwoDistinctCharacters {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();  // record the last index of current two chars

        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.size() < 2 || map.containsKey(s.charAt(i))) {   // continue increasing
                map.put(s.charAt(i), i);
            } else {        // a 3rd char appears
                int frontOfTwoChars = Integer.MAX_VALUE;
                for (int index : map.values()) {
                    frontOfTwoChars = Math.min(frontOfTwoChars, index);
                }
                max = Math.max(max, i - start);
                start = map.remove(s.charAt(frontOfTwoChars)) + 1;
                map.put(s.charAt(i), i);
            }
        }

        return Math.max(max, s.length() - start);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));		// 3
        System.out.println(lengthOfLongestSubstringTwoDistinct("aaaabbbbccccc"));	// 9
        System.out.println(lengthOfLongestSubstringTwoDistinct("cdaba"));	// 3
        System.out.println(lengthOfLongestSubstringTwoDistinct("abaccc"));	// 3
    }

}
