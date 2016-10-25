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
        TrieNode next[] = new TrieNode[26];
    }

    public List<List<String>> wordSquares(List<String> words) {
        if (words.isEmpty()) return Collections.emptyList();

        // construct trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            add(root, word);
        }

        // try every word as beginning word
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < words.size(); ++i) {
            List<String> list = new ArrayList<>();
            list.add(words.get(i));
            helper(root, 1, words.get(0).length(), list, result);
        }

        return result;
    }

    private void helper(TrieNode root, int level, int totalLevel,
                           List<String> square, List<List<String>> result) {
        if (level == totalLevel) {
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
            square.remove(next);
        }
    }

    private List<String> match(TrieNode root, String prefix) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < prefix.length(); ++i) {
            char ch = prefix.charAt(i);
            root = root.next[ch - 'a'];
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
                        for (int j = 0; j < 26; ++j) {
                            if (node.next[j] != null) {
                                queue.offer(node.next[j]);
                            }
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
            if (root.next[ch - 'a'] == null)
                root.next[ch - 'a'] = new TrieNode();
            root = root.next[ch - 'a'];
        }
        root.word = word;
    }

    public static void main(String[] args) {
        WordSquares w = new WordSquares();

        List<String> words = Arrays.asList("area","lead","wall","lady","ball");
        System.out.println(w.wordSquares(words));

        words = Arrays.asList("abat","baba","atan","atal");
        System.out.println(w.wordSquares(words));
    }
}
