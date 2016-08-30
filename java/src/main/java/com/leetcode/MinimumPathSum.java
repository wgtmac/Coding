package com.leetcode;

/**
 * 64. Minimum Path Sum
 * 
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right
 * which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Skill:
 * DP  f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
 */

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null) return 0;
        int row = grid.length, col = grid[0].length;
        if (col <= 0 || row <= 0) return 0;
        
        int[][] f = new int[row][col];
        f[0][0] = grid[0][0];
        for (int i = 1; i < col; i++)
            f[0][i] = f[0][i - 1] + grid[0][i];
        for (int i = 1; i < row; i++)
            f[i][0] = f[i - 1][0] + grid[i][0];
        
        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++)
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];

        return f[row - 1][col - 1];
    }
}
