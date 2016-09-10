package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 212. Word Search II
 *
 * Given a 2D board and a list of words from the dictionary,
 * find all words in the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once in a word.
 * 
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * 
 * [
 *  ['o','a','a','n'],
 *  ['e','t','a','e'],
 *  ['i','h','k','r'],
 *  ['i','f','l','v']
 * ]
 * 
 * Return ["eat","oath"].
 * */
public class WordSearchII {
	
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();  // root of trie
        for (String word : words)
            insert(root, word);

        Set<String> foundWords = new HashSet<>();

        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[i].length; ++j)
                search(root, board, i, j, new boolean[board.length][board[0].length], "", foundWords);

        return new ArrayList<>(foundWords);
    }

    private void search(TrieNode node, char[][] board, int i, int j,
                        boolean[][] path, String matched, Set<String> foundWords) {

        if (node.next[board[i][j] - 'a'] != null) {   // continue matching prefix
            node = node.next[board[i][j] - 'a'];
            matched += board[i][j];
            path[i][j] = true;

            // find a word, cannot stop here because it may continue match
            if (node.isWordEnd)
                foundWords.add(matched);

            // walk around
            if (i - 1 >= 0 && !path[i - 1][j])
                search(node, board, i - 1, j, path, matched, foundWords);

            if (i + 1 < board.length && !path[i + 1][j])
                search(node, board, i + 1, j, path, matched, foundWords);

            if (j - 1 >= 0 && !path[i][j - 1])
                search(node, board, i, j - 1, path, matched, foundWords);

            if (j + 1 < board[0].length && !path[i][j + 1])
                search(node, board, i, j + 1, path, matched, foundWords);

            path[i][j] = false;
        }
    }


    private void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.next[ch - 'a'] == null)
                node.next[ch - 'a'] = new TrieNode();
            node = node.next[ch - 'a'];
        }
        node.isWordEnd = true;
    }

    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWordEnd = false;
    }
}
