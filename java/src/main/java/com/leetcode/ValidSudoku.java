package com.leetcode;

/**
 * 36. Valid Sudoku
 *
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles
 * 
 * The Sudoku board could be partially filled,
 * where empty cells are filled with the character '.'.
 *
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 * 
 * Skill:
 * 用HashSet去标记每行每列每小方块
 * 
 * 可以用一个数组重复利用 分别针对行，列和小方块
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        List<Set<Character>> rows = new ArrayList<>(n),
                cols = new ArrayList<>(n), blocks = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            blocks.add(new HashSet<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;
                
                if (!rows.get(i).contains(board[i][j])
                  && !cols.get(j).contains(board[i][j])
                  && !blocks.get((i / 3) * 3 + j / 3).contains(board[i][j]) ) {
                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    blocks.get((i / 3) * 3 + j / 3).add(board[i][j]);
                } else
                    return false;
            }
        }
        
        return true;
    }
}
