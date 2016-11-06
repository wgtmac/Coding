package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 266. Palindrome Permutation
 *
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * For example,
 * "code" -> False, "aab" -> True, "carerac" -> True.
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        int oddCount = 0;

        for (int count : map.values()) {
            if (count % 2 != 0)
                oddCount++;
        }

        return oddCount <= 1;
    }
}
