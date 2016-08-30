package com.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N-Queens
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 *
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 *
 * Hint:
 * diagonal line satisfies: i - j is fixed
 */


public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; ++i)
            Arrays.fill(board[i], '.');
        helper(board, 0, result);
        return result;
    }

    private void helper(char[][] board, int rowIndex, List<List<String>> result) {
        if (rowIndex == board.length) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < board.length; ++i)
                list.add(new String(board[i]));
            result.add(list);
            return;
        }

        for (int colIndex = 0; colIndex < board[rowIndex].length; ++colIndex) {
            if (isValid(board, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                helper(board, rowIndex + 1, result);
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
