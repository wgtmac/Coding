package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 *
 * Find the length of the longest substring T of a given string (consists of
 * lowercase letters only) such that every character in T appears no less than
 * k times.
 *
 * Example 1:
 *   Input:
 *   s = "aaabb", k = 3
 *   Output:
 *   3
 *
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 *
 * Example 2:
 *   Input:
 *   s = "ababbc", k = 2
 *   Output:
 *   5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringwithAtLeastKRepeatingCharacters l = new LongestSubstringwithAtLeastKRepeatingCharacters();
        System.out.print(l.longestSubstring("aaabb", 3));
    }

    public int longestSubstring(String s, int k) {
        // init map: ch -> accumulate count
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            int[] count = map.get(ch);
            if (count == null) {
                count = new int[s.length()];
                map.put(ch, count);
            }
            count[i] = 1;
        }

        // pruning
        int maxCount = 0;
        for (int[] count : map.values()) {
            for (int i = 1; i < count.length; ++i) {
                count[i] += count[i - 1];
            }
            maxCount = Math.max(maxCount, count[count.length - 1]);
        }
        if (maxCount < k) return 0;

        int len = s.length();
        while (len > 0) {
            for (int start = 0, end = start + len - 1; end < s.length(); ++start, ++end) {
                int minCount = Integer.MAX_VALUE;
                for (int[] count : map.values()) {
                    int rangeCount = count[end] - (start == 0 ? 0 : count[start - 1]);
                    if (rangeCount > 0) {
                        minCount = Math.min(minCount, rangeCount);
                    }
                }
                if (minCount >= k) return len;
            }

            len--;
        }
        return 0;
    }
}
