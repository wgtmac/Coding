package com.leetcode;

import java.util.Arrays;

/**
 * 62. Unique Paths
 * 
 * A robot is located at the top-left corner of a m x n grid
 * (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. 
 * The robot is trying to reach the bottom-right corner of the grid
 * (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 */
public class UniquePaths {
	/*
	f[x][y]: how many different ways to reach x,y
	f[x][y] = f[x][y-1] + f[x-1][y]
	f[..][0] = 1  f[0][..] = 1
	f[m-1][n-1]
	*/
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;

        int[][] waysToGetHere = new int[m][n];
        Arrays.fill(waysToGetHere[0], 1);
        for (int i = 1; i < m; i++)
            waysToGetHere[i][0] = 1;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                waysToGetHere[i][j] =
                        waysToGetHere[i - 1][j] + waysToGetHere[i][j - 1];

        return waysToGetHere[m - 1][n - 1];
    }
}
