package com.leetcode;

/**
 * 289. Game of Life
 *
 * According to the Wikipedia's article: "The Game of Life, also known simply as
 * Life, is a cellular automaton devised by the British mathematician John Horton
 * Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or
 * dead (0). Each cell interacts with its eight neighbors (horizontal, vertical,
 * diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 *
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at
 * the same time: You cannot update some cells first and then use their updated
 * values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the
 * board is infinite, which would cause problems when the active area encroaches
 * the border of the array. How would you address these problems?
 */
public class GameOfLife {
    final static int DEAD = 0, LIVE = 1, GONNA_LIVE = -1, GONNA_DIE = 2;

    public void gameOfLife(int[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                count = countLiveNeighbours(board, i, j);
                if (board[i][j] == DEAD && count == 3) {
                    board[i][j] = GONNA_LIVE;
                } else if (board[i][j] == LIVE && (count < 2 || count > 3)) {
                    board[i][j] = GONNA_DIE;
                }
            }
        }

        // refresh
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == GONNA_DIE)
                    board[i][j] = 0;
                else if (board[i][j] == GONNA_LIVE)
                    board[i][j] = 1;
            }
        }
    }

    private int countLiveNeighbours(int[][] board, int i, int j) {
        int[] diff = {0, -1, 1};
        int I, J, count = 0;
        for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < 3; ++c) {
                if (r == 0 && c == 0) continue;
                I = i + diff[r]; J = j + diff[c];
                if (0 <= I && I < board.length && 0 <= J && J < board[0].length) {
                    if (board[I][J] >= LIVE) count++;
                }
            }
        }
        return count;
    }

}
