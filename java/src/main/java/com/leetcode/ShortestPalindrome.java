package com.leetcode;

/**
 * 214. Shortest Palindrome
 *
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 *
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 *
 * Hint:
 * Start from middle, try to figure out the longest palindrome start from beginning.
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
        if (s.length() <= 1) return s;

        int minToAdd = s.length() - 1;
        boolean matched;

        // find if s(0, i) and s(i, 2 * i) are symmetric
        for (int i = (s.length() - 1) / 2; i >= 0; --i) {
            matched = true;

            for (int j = 0; i - j >= 0; ++j) {
                if (s.charAt(i + j) != s.charAt(i - j)) {
                    matched = false;
                    break;
                }
            }

            if (matched){
                minToAdd = Math.min(minToAdd, s.length() - i * 2 - 1);
                break;
            }
        }

        // find if s(0, i - 1) and s(i, 2 * i - 1) are symmetric
        for (int i = s.length() / 2; i >= 0; --i) {
            matched = true;

            for (int j = 0; i - 1 - j >= 0; ++j) {
                if (s.charAt(i + j) != s.charAt(i - 1 - j)) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                minToAdd = Math.min(minToAdd, s.length() - i * 2);
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1, j = 0; j < minToAdd; --i, ++j)
            sb.append(s.charAt(i));

        return sb.toString() + s;
    }
}
