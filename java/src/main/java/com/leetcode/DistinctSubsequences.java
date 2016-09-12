package com.leetcode;

/**
 * 115. Distinct Subsequences
 * 
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original string
 * by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters.
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 * 
 * Skill: 
 * 动态规划，找T的前j个在i里面有多少个不重复出现
 * f[i - 1][j - 1] means each former subseq still exists, and add the same char as tail 每个包含i
 * f[i - 1][j]  means subseq without the last one 每个不包含i
 * 
 * f[i][j] = f[i-1][j-1] + f[i - 1][j]
 * */

public class DistinctSubsequences {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0)
            return 0;
        
        // 0 为空串
        int[][] matched = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i < S.length() + 1; i++)
            matched[i][0] = 1;
        
        for (int j = 1; j < T.length() + 1; j++) {
        	for (int i = 1; i < S.length() + 1; i++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    // f[i - 1][j - 1] means each former subseq still exists, and add the same char as tail
                    // f[i - 1][j]     means subseq without the last one
                    matched[i][j] = matched[i - 1][j - 1] + matched[i - 1][j];
                }
                else {
                    // new char S[i - 1] is useless
                    matched[i][j] = matched[i - 1][j];
                }
            }
        }
        
        return matched[S.length()][T.length()];
    }
}
