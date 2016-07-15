package com.leetcode;

import java.util.*;

/**
 * 336. Palindrome Pairs
 *
 Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
 so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]

 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {

    private static class TrieNode {
        private int index = -1;
        private Map<Character, TrieNode> children = new HashMap<>();   // 'a' ~ 'z'
    }

    // construct trie
    private TrieNode init(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; ++i)
            add(root, words[i], 0, i);
        return root;
    }

    // construct trie recursively
    private void add(TrieNode root, String word, int i, int index) {
        if (word.equals("")) {   // edge case: an empty string exists
            root.index = index;
        } else {
            TrieNode currNode = root.children.get(word.charAt(i));
            if (currNode == null) {
                currNode = new TrieNode();
                root.children.put(word.charAt(i), currNode);
            }

            if (i == word.length() - 1)  // reach end of a string
                currNode.index = index;
            else
                add(currNode, word, i + 1, index);
        }
    }

    private void match(TrieNode node, String word, int rightIndex, List<List<Integer>> list) {
        // "" on the left
        checkAndAdd(node, rightIndex, word, word.length() - 1, list);

        boolean stillMatching = true;
        int i = word.length() - 1;
        while (i >= 0 && stillMatching) {
            char ch = word.charAt(i);
            TrieNode nextNode = node.children.get(ch);
            if (nextNode != null) {
                checkAndAdd(nextNode, rightIndex, word, i - 1, list);
                node = nextNode;
                --i;
            } else {
                stillMatching = false;
            }
        }

        if (stillMatching) {
            matchMiddle(node, new StringBuilder(), rightIndex, list);
        }
    }

    private void matchMiddle(TrieNode node, StringBuilder sb, int rightIndex, List<List<Integer>> list) {
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            TrieNode next = node.children.get(ch);
            if (next != null) {
                sb.append(ch);
                // can only do it here, to avoid duplicate matching
                checkAndAdd(next, rightIndex, sb.toString(), sb.length() - 1, list);
                matchMiddle(next, sb, rightIndex, list);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private void checkAndAdd(TrieNode node, int rightIndex, String str, int strLen, List<List<Integer>> list) {
        if (node.index != -1 && node.index != rightIndex && isPalindrome(str, 0, strLen)) {
            list.add(new ArrayList<Integer>() {{ add(node.index); add(rightIndex); }});
        }
    }

    private boolean isPalindrome(String str, int start, int end) {
        for (; start < end; start++, end--) {
            if (str.charAt(start) != str.charAt(end)) return false;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<>();
        TrieNode root = init(words);
        for (int i = 0; i < words.length; ++i) {
            match(root, words[i], i, list);
        }
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
