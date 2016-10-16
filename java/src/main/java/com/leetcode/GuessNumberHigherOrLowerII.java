package com.leetcode;

/**
 * 375. Guess Number Higher or Lower II
 *
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked. Every
 * time you guess wrong, I'll tell you whether the number I picked is higher or
 * lower. However, when you guess a particular number x, and you guess wrong, you
 * pay $x. You win the game when you guess the number I picked.
 *
 * Example:
 *
 * n = 10, I pick 8.
 *
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *
 * Game over. 8 is the number I picked.
 *
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 */

/**
 * DP solution
 * for length of 2: [i, i + 1], choose i
 * for length >= 3, iterate every number k, compute min k+max(f[i][k-1],f[k+1][j])
 */
public class GuessNumberHigherOrLowerII {
    public int getMoneyAmount(int n) {
        // f[i][j]: min cost to win in range [i, j]
        int[][] f = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                // f[i][i+1] = i
                f[i][j] = (j == i + 1 ? i : Integer.MAX_VALUE);
            }
        }

        // f[i][j] = min(max(f[i][k-1], f[k+1][j]) + k) for k
        for (int len = 3; len <= n; ++len) {
            for (int i = 1, j = i + len - 1; j <= n; ++i, ++j) {
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
                }
            }
        }

        return f[1][n];
    }
}
