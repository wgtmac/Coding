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
 *  if (str1[i - 1] != str2[j - 1])
 *     f[i - 1][j - 1] : swap[i - 1]
 *     f[i][j - 1]     : add[j - 1]
 *     f[i - 1][j]     : delete[i - 1]     
 *     
 *     E[i,j] = min{ E[i-1,j-1] ,E[i-1,j] + 1, E[i,j-1] + 1}, 当A[i] = B[j]时；
 *     E[i,j] = min{ E[i-1,j-1] + 1, E[i-1,j] + 1, E[i, j-1] +1},当A[i] /= B[j]时。
 *     
 *     1）如果word1[i-1]=word2[j-1]，res[i][j]=res[i-1][j-1]，因为新加入的字符不用编辑；
 *     2）如果word1[i-1]!=word2[j-1]，那么我们就考虑三种操作，
 *     如果是插入word1，那么res[i][j]=res[i-1][j]+1，也就是取word1前i-1个字符和word2前j个字符的最好结果，然后添加一个插入操作；
 *     如果是插入word2，那么res[i][j]=res[i][j-1]+1，道理同上面一种操作；
 *     如果是替换操作，那么类似于上面第一种情况，但是要加一个替换操作（因为word1[i-1]和word2[j-1]不相等），所以递推式是res[i][j]=res[i-1][j-1]+1。
 *     上面列举的情况包含了所有可能性
 *     为什么没有删除操作，其实这里添加一个插入操作永远能得到与一个删除操作相同的效果，所以删除不会使最少操作数变得更好，
 *     因此如果我们是正向考虑，则不需要删除操作。取上面几种情况最小的操作数，即为第二种情况的结果，即res[i][j] = min(res[i-1][j], res[i][j-1], res[i-1][j-1])+1。
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        
        int[][] f = new int[str1.length + 1][str2.length + 1];

        for (int i = 1; i <= str1.length; i++) {
            f[i][0] = i; // 删i个
            for (int j = 1; j <= str2.length; j++) {
                f[0][j] = j;			// 加i个
                f[i][j] = (str1[i - 1] == str2[j - 1]) ? f[i - 1][j - 1] :
                          (1 + Math.min(f[i - 1][j - 1], Math.min(f[i][j - 1], f[i - 1][j])));
                          // NOTICE: f[i - 1][j - 1] must be added
            }
        }
        
        return f[str1.length][str2.length];
    }
}
