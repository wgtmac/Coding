package com.leetcode;

import java.util.*;

/**
 * 425. Word Squares
 *
 * Given a set of words (without duplicates), find all word squares you can build from them.
 *
 * A sequence of words forms a valid word square if the kth row and column read
 * the exact same string, where 0 ≤ k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word
 * square because each word reads the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 *
 * Note:
 *
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 *
 * Example 1:
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *  [
 *   "wall",
 *   "area",
 *   "lead",
 *   "lady"
 *  ],
 *  [
 *   "ball",
 *   "area",
 *   "lead",
 *   "lady"
 *  ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 * Example 2:
 *
 * Input:
 * ["abat","baba","atan","atal"]
 *
 * Output:
 * [
 *  [
 *   "baba",
 *   "abat",
 *   "baba",
 *   "atan"
 *  ],
 *  [
 *   "baba",
 *   "abat",
 *   "baba",
 *   "atal"
 *  ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 */
public class WordSquares {

    private static class TrieNode {
        String word = null;
        Map<Character, TrieNode> next = new HashMap<>();
    }

    List<List<String>> wordSquares(String[] words) {
        if (words.length == 0) return Collections.emptyList();

        // construct trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            add(root, word);
        }

        // try every word as beginning word
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            list.add(words[i]);
            helper(root, 1, words[i].length(), list, result);
            list.remove(list.size() - 1);
        }

        return result;
    }

    private void helper(TrieNode root, int level, int totalLevel,
                        List<String> square, List<List<String>> result) {
        if (level == totalLevel) {
            //System.out.println(level + " " + square);
            result.add((List<String>) ((ArrayList<String>)(square)).clone());
            return;
        }

        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            // catch column level-1 from each row
            prefix.append(square.get(i).charAt(level));
        }

        List<String> nextLevel = match(root, prefix.toString());
        for (String next : nextLevel) {
            square.add(next);
            helper(root, level + 1, totalLevel, square, result);
            square.remove(square.size() - 1);
        }
    }

    private List<String> match(TrieNode root, String prefix) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < prefix.length(); ++i) {
            root = root.next.get(prefix.charAt(i));
            if (root == null) break;
        }

        if (root != null) {
            Queue<TrieNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    TrieNode node = queue.poll();
                    if (node.word != null) {
                        words.add(node.word);
                    } else {
                        for (TrieNode next : node.next.values()) {
                            queue.offer(next);
                        }
                    }
                }
            }
        }

        return words;
    }

    private void add(TrieNode root, String word) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!root.next.containsKey(ch))
                root.next.put(ch, new TrieNode());
            root = root.next.get(ch);
        }
        root.word = word;
    }

    public static void main(String[] args) {
        WordSquares w = new WordSquares();
        String[] words = {"abaa","aaab","baaa","aaba"};
        System.out.println(w.wordSquares(words));
    }
}
