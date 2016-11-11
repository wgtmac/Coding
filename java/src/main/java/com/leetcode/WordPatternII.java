package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 291. Word Pattern II
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter
 * in pattern and a non-empty substring in str.
 *
 * Examples:
 *   1. pattern = "abab", str = "redblueredblue" should return true.
 *   2. pattern = "aaaa", str = "asdasdasdasd" should return true.
 *   3. pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {

        // find max allowed length
        Map<Character, Integer> charCnt = new HashMap<>();
        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern.charAt(i);
            charCnt.put(ch, charCnt.getOrDefault(ch, 0) + 1);
        }
        int minCnt = Integer.MAX_VALUE;
        for (int cnt : charCnt.values()) {
            minCnt = Math.min(minCnt, cnt);
        }
        int maxLen = (str.length() - (pattern.length() - minCnt) * 1) / minCnt + 1;

        // backtracking, try each possible matching
        Map<Character, String> map = new HashMap<>();
        Set<String> used = new HashSet<>();
        return helper(pattern, 0, str, 0, map, used, maxLen);
    }

    private boolean helper(String pattern, int ptIdx, String str, int strStart,
                           Map<Character, String> map, Set<String> used, int maxLen) {

        if (ptIdx == pattern.length() && strStart == str.length()) return true;

        StringBuilder word = new StringBuilder();
        for (int strEnd = strStart;
             strEnd < Math.min(strStart + maxLen, str.length()) && ptIdx < pattern.length();
             ++strEnd) {
            word.append(str.charAt(strEnd));

            char patternCh = pattern.charAt(ptIdx);

            if (!map.containsKey(patternCh) && !used.contains(word.toString())) {
                map.put(patternCh, word.toString());
                used.add(word.toString());

                if (helper(pattern, ptIdx + 1, str, strEnd + 1, map, used, maxLen)){
                    return true;
                } else {
                    map.remove(patternCh);
                    used.remove(word.toString());
                }
            } else if (map.containsKey(patternCh) && word.toString().equals(map.get(patternCh))) {
                if (helper(pattern, ptIdx + 1, str, strEnd + 1, map, used, maxLen)){
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        WordPatternII wp = new WordPatternII();
        System.out.println(wp.wordPatternMatch("abab", "redblueredblue"));
        System.out.println(wp.wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(wp.wordPatternMatch("aabb", "xyzabcxzyabc"));
    }
}
