package com.leetcode;

/**
 * 37. Sudoku Solver
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 * 
 * Skill: 
 * 暴力法尝试
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		SetGroup setGroup = new SetGroup (board);
		helper(board, 0, setGroup);
	}

	public boolean helper (char[][] b, int index, SetGroup setGroup) {
		if (setGroup.space == 0) return true;

		for (; b[index / 9][index % 9] != '.'; ++index);

		int i = index / 9, j = index % 9;
		for (char ch = '1'; ch <= '9'; ch++) {
			if (setGroup.isValid(i, j, ch)) {
				setGroup.add(i, j, ch);
				if (helper(b, index + 1, setGroup))
					return true;
				setGroup.remove(i, j, ch);
			}
		}

		return false;
	}

	private static class SetGroup {
		List<Set<Character>> rows, cols;
		List<List<Set<Character>>>blocks;
		int space;
		char[][] b;

		SetGroup (char[][] b) {
			rows = new ArrayList<>(9);
			cols = new ArrayList<>(9);
			for (int i = 0; i < 9; i++) {
				rows.add(new HashSet<>(9));
				cols.add(new HashSet<>(9));
			}

			blocks = new ArrayList<>(3);
			for (int i = 0; i < 3; ++i) {
				blocks.add(new ArrayList<>(3));
				for (int j = 0; j < 3; ++j)
					blocks.get(i).add(new HashSet<>(9));
			}

			space = 81;
			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j) {
					if (b[i][j] != '.') {
						rows.get(i).add(b[i][j]);
						cols.get(j).add(b[i][j]);
						blocks.get(i / 3).get(j / 3).add(b[i][j]);
						space--;
					}
				}
			}

			this.b = b;
		}

		boolean isValid (int i, int j, char ch) {
			return (!rows.get(i).contains(ch)) &&
					(!cols.get(j).contains(ch)) &&
					(!blocks.get(i / 3).get(j / 3).contains(ch));
		}

		void add (int i, int j, char ch) {
			rows.get(i).add(ch);
			cols.get(j).add(ch);
			blocks.get(i / 3).get(j / 3).add(ch);
			b[i][j] = ch;
			space--;
		}

		void remove (int i, int j, char ch) {
			rows.get(i).remove(ch);
			cols.get(j).remove(ch);
			blocks.get(i / 3).get(j / 3).remove(ch);
			b[i][j] = '.';
			space++;
		}
	}
}
