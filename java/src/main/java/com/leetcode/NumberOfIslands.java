package com.leetcode;

/**
 * 200. Number of Islands
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting adjacent
 * lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 *
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    ++count;
                    connectIsland(grid, i, j);
                }
            }
        }
        return count;
    }

    private void connectIsland(char[][] grid, int i, int j) {
        grid[i][j] = '2';
        if (i - 1 >= 0 && grid[i - 1][j] == '1')
            connectIsland(grid, i - 1, j);
        if (i + 1 < grid.length && grid[i + 1][j] == '1')
            connectIsland(grid, i + 1, j);
        if (j - 1 >= 0 && grid[i][j - 1] == '1')
            connectIsland(grid, i, j - 1);
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1')
            connectIsland(grid, i, j + 1);
    }
}
