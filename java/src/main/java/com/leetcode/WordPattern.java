package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 290. Word Pattern
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter
 * in pattern and a non-empty word in str.
 *
 * Examples:
 * 1. pattern = "abba", str = "dog cat cat dog" should return true.
 * 2. pattern = "abba", str = "dog cat cat fish" should return false.
 * 3. pattern = "aaaa", str = "dog cat cat dog" should return false.
 * 4. pattern = "abba", str = "dog dog dog dog" should return false.
 *
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains
 * lowercase letters separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) return false;

        Map<String, Character> map = new HashMap<>();
        Set<Character> used = new HashSet<>();
        for (int i = 0; i < words.length; ++i) {
            if (!map.containsKey(words[i])) {
                if (used.contains(pattern.charAt(i)))
                    return false;
                map.put(words[i], pattern.charAt(i));
                used.add(pattern.charAt(i));
            } else if (pattern.charAt(i) != map.get(words[i])) {
                return false;
            }
        }

        return true;
    }
}
