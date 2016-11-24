package com.leetcode;

/**
 * 329. Longest Increasing Path in a Matrix
 *
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 *
 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                max = Math.max(max, visit(matrix, i, j, cache));
            }
        }
        return max;
    }

    private int visit(int[][] matrix, int row, int col, int[][] cache) {
        if (row < 0 || row > matrix.length || col < 0 || col > matrix[0].length)
            return 0;

        if (cache[row][col] == 0) {
            int max = 0;

            if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col])
                max = Math.max(max, visit(matrix, row - 1, col, cache));
            if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col])
                max = Math.max(max, visit(matrix, row + 1, col, cache));
            if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col])
                max = Math.max(max, visit(matrix, row, col - 1, cache));
            if (col + 1 < matrix[0].length && matrix[row ][col + 1] > matrix[row][col])
                max = Math.max(max, visit(matrix, row, col + 1, cache));

            cache[row][col] = max + 1;
        }

        return cache[row][col];
    }
}
