package com.leetcode;

import java.util.*;

/**
 * 411. Minimum Unique Word Abbreviation
 *
 * A string such as "word" contains the following abbreviations:
 *
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1",
 * "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *
 * Given a target string and a set of strings in a dictionary, find an abbreviation
 * of this target string with the smallest possible length such that it does not
 * conflict with abbreviations of the strings in the dictionary.
 *
 * Each number or letter in the abbreviation is considered length = 1.
 * For example, the abbreviation "a32bc" has length = 4.
 *
 * Note:
 * In the case of multiple answers as shown in the second example below, you may
 * return any one of them.
 * Assume length of target string = m, and dictionary size = n. You may assume
 * that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 *
 * Examples:
 * "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
 *
 * "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include
 * "ap3", "a3e", "2p2", "3le", "3l1").
 */
public class MinimumUniqueWordAbbreviation {

    private String minAbbr;
    private int minLen;

    public String minAbbreviation(String target, String[] dictionary) {
        // only leave words of same length in dictionary
        List<String> dict = new LinkedList<>();
        for (String word : dictionary) {
            if (word.length() == target.length())
                dict.add(word);
        }

        // init global variables
        minAbbr = target;
        minLen = target.length();

        // perform backtracking
        helper(target, "", 0, 0, dict);
        return minAbbr;
    }

    private void helper(String target, String abbr, int index, int abbrLength,
                        List<String> dict) {
        if (abbrLength >= minLen) return;   // pruning, very important for time
        if (index == target.length()) {
            for (String word : dict) {
                if (validWordAbbreviation(word, abbr))
                    return;
            }
            minAbbr = abbr;
            minLen = abbrLength;
        } else {
            // no abbr at curr point
            helper(target, abbr + target.charAt(index), index + 1, abbrLength + 1, dict);;

            // abbr from curr point
            if (abbr.isEmpty() || !Character.isDigit(abbr.charAt(abbr.length() - 1))) {
                for (int len = 1; index + len <= target.length(); ++len) {
                    helper(target, abbr + len, index + len, abbrLength + 1, dict);
                }
            }
        }
    }

    private boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;

        while (i < word.length() && j < abbr.length()) {
            if (!Character.isDigit(abbr.charAt(j))) {
                if (word.charAt(i++) != abbr.charAt(j++))
                    return false;
            } else {
                int num = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + abbr.charAt(j++) - '0';
                }
                if ((i += num) > word.length())
                    return false;
            }
        }

        return i == word.length() && j == abbr.length();
    }
}
