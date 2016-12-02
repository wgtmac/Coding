package com.leetcode;

import java.util.Arrays;

/**
 * 392. Is Subsequence
 *
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * You may assume that there is only lower case English letters in both s and t.
 * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 *
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing the
 * relative positions of the remaining characters. (ie, "ace" is a subsequence of
 * "abcde" while "aec" is not).
 *
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 *
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you
 * want to check one by one to see if T has its subsequence. In this scenario,
 * how would you change your code?
 */
public class IsSubsequence {
    public boolean isSubsequence_GREEDY(String s, String t) {
        int i = 0, j = 0;
        while (i != s.length() && j != t.length()) {
            if (s.charAt(i) == t.charAt(j++)) {
                ++i;
            }
        }
        return i == s.length();
    }

    public boolean isSubsequence(String s, String t) {
        boolean[] f = new boolean[t.length() + 1], f_backup = new boolean[t.length() + 1];
        Arrays.fill(f_backup, true);

        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= t.length(); ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[j] = f_backup[j - 1];
                } else {
                    f[j] = f[j - 1];
                }
            }
            boolean[] tmp = f_backup;
            f_backup = f;
            f = tmp;
            Arrays.fill(f, false);
        }

        return f_backup[t.length()];
    }

    public boolean isSubsequence_MLE(String s, String t) {
        boolean[][] f = new boolean[s.length() + 1][t.length() + 1];
        Arrays.fill(f[0], true);

        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= t.length(); ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];    // same char, continue matching
                } else {
                    f[i][j] = f[i][j - 1];        // try to continue previous matching
                }
            }
        }

        return f[s.length()][t.length()];
    }
}
