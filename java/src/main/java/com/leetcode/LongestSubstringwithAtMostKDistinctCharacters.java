package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * For example, Given s = “eceba” and k = 2,
 *
 * T is "ece" which its length is 3.
 */
public class LongestSubstringwithAtMostKDistinctCharacters {
    static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();  // record the last index of current two chars

        int start = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.size() < k || map.containsKey(s.charAt(i))) {   // continue increasing
                map.put(s.charAt(i), i);
            } else {        // k+1 th char appears
                max = Math.max(max, i - start);

                while (map.size() == k) {
                    if (map.get(s.charAt(start)) == start) {
                        map.remove(s.charAt(start));
                    }
                    start++;
                }

                map.put(s.charAt(i), i);
            }
        }

        return Math.max(max, s.length() - start);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));		// 3
        System.out.println(lengthOfLongestSubstringKDistinct("aaaabbbbccccc", 2));	// 9
        System.out.println(lengthOfLongestSubstringKDistinct("cdaba", 2));	// 3
        System.out.println(lengthOfLongestSubstringKDistinct("abaccc", 2));	// 4
    }
}
