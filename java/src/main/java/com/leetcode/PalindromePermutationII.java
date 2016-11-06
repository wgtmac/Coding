package com.leetcode;

import java.util.*;

/**
 * 267. Palindrome Permutation II
 *
 * Given a string s, return all the palindromic permutations (without duplicates)
 * of it. Return an empty list if no palindromic permutation could be form.
 *
 * For example:
 *
 * Given s = "aabb", return ["abba", "baab"].
 *
 * Given s = "abc", return [].
 */
public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        Character oddChar = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                if (oddChar != null)
                    return Collections.emptyList();
                oddChar = entry.getKey();
            }
        }

        List<String> ans = new ArrayList<>();
        if (oddChar != null) {
            map.put(oddChar, map.get(oddChar) - 1);
            if (map.get(oddChar) == 0)
                map.remove(oddChar);
        }
        helper(map, "", ans, oddChar);

        return ans;
    }

    private void helper(Map<Character, Integer> map, String str,
                        List<String> ans, Character oddChar) {
        if (map.isEmpty()) {
            ans.add(str + (oddChar == null ? "" : oddChar) + new StringBuilder(str).reverse().toString());
        } else {
            for (char ch : map.keySet()) {
                Map<Character, Integer> cloneMap = new HashMap<>(map);

                cloneMap.put(ch, cloneMap.get(ch) - 2);
                if (cloneMap.get(ch) == 0)
                    cloneMap.remove(ch);

                helper(cloneMap, str + ch, ans, oddChar);
            }
        }
    }
}
