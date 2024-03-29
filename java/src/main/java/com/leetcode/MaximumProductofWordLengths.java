package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 318. Maximum Product of Word Lengths
 *
 * Given a string array words, find the maximum val of length(word[i]) * length(word[j])
 * where the two words do not share common letters. You may assume that each word
 * will contain only lower case letters. If no such two words exist, return 0.
 *
 * Example 1:
 * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * Return 16
 * The two words can be "abcw", "xtfn".
 *
 * Example 2:
 * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * Return 4
 * The two words can be "ab", "cd".
 *
 * Example 3:
 * Given ["a", "aa", "aaa", "aaaa"]
 * Return 0
 * No such pair of words.
 */
public class MaximumProductofWordLengths {
    public int maxProduct(String[] words) {
        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            int mask = 0;
            for (char ch : words[i].toCharArray())
                mask |= 1 << (ch - 'a');
            masks[i] = mask;
        }

        int max = 0;
        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if ((masks[i] & masks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }

        return max;
    }
}
