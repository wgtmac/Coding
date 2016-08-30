package com.leetcode;

import java.util.Arrays;

/**
 * 52. N-Queens II
 *
 * Follow up for N-Queens problem.
 *
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 * Skill:
 * Directly use previous question's solution
 * 在对角线上 ====  row_down - row_up = abs (col_down - col_up)
 */

public class NQueensII {
    public int totalNQueens(int n) {
        int[] result = {0};
        char[][] board = new char[n][n];
        for (int i = 0; i < n; ++i)
            Arrays.fill(board[i], '.');
        helper(board, 0, result);
        return result[0];
    }


    private void helper(char[][] board, int rowIndex, int[] count) {
        if (rowIndex == board.length) {
            count[0] += 1;
            return;
        }

        for (int colIndex = 0; colIndex < board[rowIndex].length; ++colIndex) {
            if (isValid(board, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                helper(board, rowIndex + 1, count);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < row; ++i)
            if (board[i][col] == 'Q')
                return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j)
            if (board[i][j] == 'Q') return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; --i, ++j)
            if (board[i][j] == 'Q') return false;

        return true;
    }

}
