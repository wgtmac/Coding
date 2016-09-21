package com.leetcode;

/**
 * 97. Interleaving String
 * 
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * 
 * Skill:
 * 动态规划
 * f[i][j]表示S1前i个和S2前j个是否可以表示S3前i+j个
 * f[i][j] = f[i-1][j] && s1[i] ==是s3[i+j-1]
 *        OR f[i][j-1] && s2[j] 是s3[i+j-1]
 */

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        if (s1.length() + s2.length() != s3.length()) return false;
        
        boolean[][] f = new boolean[s1.length() + 1][s2.length() + 1];
        f[0][0] = true;
        for (int i = 1; i < s1.length() + 1; i++) 
            f[i][0] = s1.substring(0, i).equals(s3.substring(0, i));
        for (int i = 1; i < s2.length() + 1; i++)
            f[0][i] = s2.substring(0, i).equals(s3.substring(0, i));

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                f[i][j] = (f[i][j - 1] == true && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                       || (f[i - 1][j] == true && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return f[s1.length()][s2.length()];
    }
}
