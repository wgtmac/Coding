package com.leetcode;

/**
 * 63. Unique Paths II
 * 
 * Follow up for "Unique Paths":
 * Now consider if some obstacles are added to the grids.
 * How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 *
 * Note: m and n will be at most 100.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        if (col <= 0 || row <= 0) return 0;
        
        int[][] waysToGetHere = new int[row][col];
        // if there's an obstacle, all subsequent ones cannot be reached
        for (int i = 0; i < col && obstacleGrid[0][i] != 1; i++)
            waysToGetHere[0][i] = 1;
        for (int i = 0; i < row && obstacleGrid[i][0] != 1; i++)
            waysToGetHere[i][0] = 1;

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] != 1) {
                    waysToGetHere[i][j] =
                            waysToGetHere[i][j - 1] + waysToGetHere[i - 1][j];
                }

            }
        }
        return waysToGetHere[row - 1][col - 1];
    }
}
