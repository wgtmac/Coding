package com.leetcode;

/**
 * 130. Surrounded Regions
 * 
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * Skill: 
 * 找四条边的O的联通集合 其余O都变X
 * 注意坑爹的helper判断条件 别再把边界包括进去
 * */ 

public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int row = board.length, col = board[0].length;

        // left edge
        for (int i = 0; i < row; i++)
        	if (board[i][0] == 'O') helper(board, i, 0);

        // right edge
        for (int i = 0; i < row; i++)
        	if (board[i][col - 1] == 'O') helper(board, i, col - 1);

        // top edge
        for (int i = 1; i < col - 1; i++)
        	if (board[0][i] == 'O') helper(board, 0, i);

        // bottom edge
        for (int i = 1; i < col - 1; i++)
        	if (board[row - 1][i] == 'O') helper(board, row - 1, i);

        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (board[i][j] == 'O')
        		    board[i][j] = 'X';
        		else if (board[i][j] == 'S')
        		    board[i][j] = 'O';
        	}
        }
    }

    private void helper (char[][] board, int i, int j) {
        board[i][j] = 'S';
        if (i != 0 && board[i - 1][j] == 'O')
            helper (board, i - 1, j);
        if (i != board.length - 1 && 0 < j && j < board[0].length - 1 && board[i + 1][j] == 'O')
            helper (board, i + 1, j);
        if (j != 0 && board[i][j - 1] == 'O')
            helper (board, i, j - 1);
        if (j != board[0].length - 1 && 0 < i && i < board.length - 1 && board[i][j + 1] == 'O')
            helper (board, i, j + 1);
    }
}
