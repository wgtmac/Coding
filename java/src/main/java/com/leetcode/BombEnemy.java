package com.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

/**
 * 361 Bomb Enemy
 *
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0'
 * (the number zero), return the maximum enemies you can kill using one bomb.
 *
 * The bomb kills all the enemies in the same row and column from the planted
 * point until it hits the wall since the wall is too strong to be destroyed.
 *
 * Note that you can only put the bomb at an empty cell.
 *
 * Example:
 * For the given grid
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] sum = new int[row][col];

        for (int i = 0, s = 0; i < row; ++i, s = 0) {
            for (int j = 0; j < col; ++j)
                s = update(sum, grid, i, j, s);

            s = 0;
            for (int j = col - 1; j >= 0; --j)
                s = update(sum, grid, i, j, s);
        }

        for (int j = 0, s = 0; j < col; ++j, s = 0) {
            for (int i = 0; i < row; ++i)
                s = update(sum, grid, i, j, s);

            s = 0;
            for (int i = row - 1; i >= 0; --i)
                s = update(sum, grid, i, j, s);
        }

        int max = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == 'E') {    // set correct count for enemy
                    sum[i][j] += 1;
                } else if (grid[i][j] == '0') {
                    max = Math.max(max, sum[i][j]);
                }
            }
        }

        for (int i = 0; i < row; ++i) {
            System.out.println(Arrays.toString(sum[i]));
        }

        return max;
    }

    private int update(int[][] sum, char[][] grid, int i, int j, int s) {
        if (grid[i][j] == 'W')       // meet wall
            s = 0;
        else if (grid[i][j] == 'E')  // skip current enemy
            sum[i][j] += s++;
        else                         // accumulate enemies
            sum[i][j] += s;
        return s;
    }

    public static void main(String[] args) {
        BombEnemy b = new BombEnemy();
        char[][] grid =
                {{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
        System.out.println(b.maxKilledEnemies(grid));
    }
}
