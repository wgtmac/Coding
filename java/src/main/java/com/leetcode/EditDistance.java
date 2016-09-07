package com.leetcode;

/**
 * 72. Edit Distance
 * 
 * Given two words word1 and word2,
 * find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * 
 *  
 * Skill:
 * f[i][j]表示从word1前i个变成word2前j个所需操作
 *  if (str1[i] != str2[j])
 *     f[i - 1][j - 1] : replace[i]
 *     f[i][j - 1]     : add[j]
 *     f[i - 1][j]     : delete[i]
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        
        int[][] f = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word1.length(); i++) {
            f[i][0] = i; // 删i个
            for (int j = 1; j <= word2.length(); j++) {
                f[0][j] = j;			// 加i个

                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1];
                else
                    f[i][j] = 1 + Math.min(f[i - 1][j - 1],
                            Math.min(f[i][j - 1], f[i - 1][j]));
            }
        }
        
        return f[word1.length()][word2.length()];
    }
}
