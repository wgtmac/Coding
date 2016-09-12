package com.leetcode;

import java.util.Arrays;

/**
 * 132. Palindrome Partitioning II
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * */
public class PalindromePartitioningII {
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int len = s.length();
        //indicates whether substring(i, j+1) is palindrome
        boolean isPalindrome[][] = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int side = 0; (i - side >= 0) && (i + side < len) &&
                    s.charAt(i - side) == s.charAt(i + side); side++)
                isPalindrome[i - side][i + side] = true;

            for (int side = 0; (i - side >= 0) && (i + 1 + side < len) &&
                    s.charAt(i - side) == s.charAt(i + 1 + side); side++)
                isPalindrome[i - side][i + 1 + side] = true;
        }
        
        int minCuts[] = new int[len];
        minCuts[0] = 0;
        
        for (int i = 1; i < len; i++) {
            if (isPalindrome[0][i])
                minCuts[i] = 0;
            else {
                int minCut = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i])
                        minCut = Math.min(minCut, minCuts[j] + 1);
                }
                minCuts[i] = minCut;
            }

        }

        return minCuts[len - 1];
    }

    public static void main(String[] args) {
        PalindromePartitioningII pp = new PalindromePartitioningII();
        System.out.println(pp.minCut("aab"));
    }
}
