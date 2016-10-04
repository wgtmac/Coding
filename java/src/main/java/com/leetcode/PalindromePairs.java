package com.leetcode;

import java.util.*;

/**
 * 336. Palindrome Pairs
 *
 * Given a currList of unique words.
 * Find all pairs of distinct indices (i, j) in the given currList,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 *
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {
    private static class TrieNode {
        private int index = -1;
        private Map<Character, TrieNode> children = new HashMap<>(); // 'a' ~ 'z'
    }

    // construct trie
    private TrieNode construct(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; ++i) {
            TrieNode node = root;
            for (int j = 0; j < words[i].length(); ++j) {
                char ch = words[i].charAt(j);
                if (!node.children.containsKey(ch))
                    node.children.put(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.index = i;
        }
        return root;
    }

    /**
     * match the reverse word with the trie
     */
    private void trie2WordMatching(TrieNode root, String word, int wordIndex, List<List<Integer>> list) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0 && node != null; --i) {
            if (isPalindrome(word, 0, i))
                check(node, wordIndex, list);
            node = node.children.get(word.charAt(i));
        }
        check(node, wordIndex, list);

        // trie still can go down
        if (node != null)
            trailingTrieMatching(node, "", wordIndex, list);
    }

    private void trailingTrieMatching(TrieNode root, String str, int wordIndex, List<List<Integer>> list) {
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            TrieNode node = root.children.get(ch);
            if (node != null) {
                String nextStr = str + ch;
                if (isPalindrome(nextStr, 0, nextStr.length() - 1))
                    check(node, wordIndex, list);

                trailingTrieMatching(node, nextStr, wordIndex, list);
            }
        }
    }

    private void check(TrieNode node, int wordIndex, List<List<Integer>> list) {
        if (node != null && node.index != -1 && node.index != wordIndex) {
            int index = node.index;
            list.add(new ArrayList<Integer>(){{add(index); add(wordIndex);}});
        }
    }

    private boolean isPalindrome(String str, int start, int end) {
        for (; start < end; start++, end--)
            if (str.charAt(start) != str.charAt(end))
                return false;
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<>();
        TrieNode root = construct(words);
        for (int i = 0; i < words.length; ++i)
            trie2WordMatching(root, words[i], i, list);
        return list;
    }

    public static void main(String[] args) {
        PalindromePairs p = new PalindromePairs();
        System.out.println(p.palindromePairs(new String[]{"bat", "tab", "cat"}));
        System.out.println(p.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(p.palindromePairs(new String[]{"a", ""}));
        System.out.println(p.palindromePairs(new String[]{"aa", "a"}));
        System.out.println(p.palindromePairs(new String[]{"lls", "s"}));
    }
}
