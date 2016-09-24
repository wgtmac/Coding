package com.leetcode;

/**
 * 208. Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
public class ImplementTriePrefixTree {

    class TrieNode {
        boolean hasWord;
        TrieNode[] next;
        public TrieNode() {
            hasWord = false;
            next = new TrieNode[26];
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                if (node.next[word.charAt(i) - 'a'] == null)
                    node.next[word.charAt(i) - 'a'] = new TrieNode();
                node = node.next[word.charAt(i) - 'a'];
            }
            if (!word.isEmpty())
                node.hasWord = true;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = traverse(word);
            return node != null && node.hasWord;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            return traverse(prefix) != null;
        }

        private TrieNode traverse(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                if (node.next[word.charAt(i) - 'a'] == null)
                    return null;
                node = node.next[word.charAt(i) - 'a'];
            }
            return node;
        }
    }

    // Your Trie object will be instantiated and called as such:
    // Trie trie = new Trie();
    // trie.insert("somestring");
    // trie.search("key");
}
