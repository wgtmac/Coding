package com.leetcode;

/**
 * 79. Word Search
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 *  ["ABCE"],
 *  ["SFCS"],
 *  ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * 
 * Skill: 
 * 每个点都作为起点尝试
 * 用递归分别进行四个方向的判断
 * HashSet存路径！ 也可以把当前匹配到存成 #等符号 这样省去了HashSet
 * */
import java.util.HashSet;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if (helper(board, i, j, word, 0, new HashSet<>())) {
        			return true;
        		}
        	}
        }
        return false;
    }
    
    private boolean helper (char[][] board, int i, int j, String word, int pos,
                            HashSet<String> set) {
    	if (pos == word.length())
    	    return true;

        boolean ret = false;
        String key = i + " " + j;
        if (word.charAt(pos) == board[i][j] && !set.contains(key)) {
            set.add(key);

            if (set.size() == word.length())
                ret = true;

            if (!ret && i - 1 >= 0)
                ret = helper(board, i - 1, j, word, pos + 1, set);	// up

            if (!ret && i + 1 < board.length)
                ret = helper(board, i + 1, j, word, pos + 1, set);  //down

            if (!ret && j - 1 >= 0)
                ret = helper(board, i, j - 1, word, pos + 1, set);	// left

            if (!ret && j + 1 < board[0].length)
                ret = helper(board, i, j + 1, word, pos + 1, set);	// right

            set.remove(key);
        }
    	
    	return ret;
    }
}
