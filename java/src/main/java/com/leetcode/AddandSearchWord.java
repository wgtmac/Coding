package com.leetcode;

/**
 * 211. Add and Search Word - Data structure design
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 * For example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
*/

public class AddandSearchWord {
    private static class WordDictionary {
        private TrieNode root = new TrieNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            if (!word.isEmpty()) {
                TrieNode trieNode = root;
                for (int i = 0; i < word.length(); ++i) {
                    if (trieNode.next[word.charAt(i) - 'a'] == null)
                        trieNode.next[word.charAt(i) - 'a'] = new TrieNode();
                    trieNode = trieNode.next[word.charAt(i) - 'a'];
                }
                trieNode.isSet = true;
            }
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            if (word == null || word.isEmpty()) return false;
            return helper(word, 0, root);
        }

        private boolean helper(String word, int index, TrieNode trieNode) {
            if (index == word.length()) return trieNode.isSet;

            if (word.charAt(index) == '.') {
                for (int i = 0; i < 26; ++i) {
                    if (trieNode.next[i] != null && helper(word, index + 1, trieNode.next[i])) {
                        return true;
                    }
                }
                return false;
            } else {
                return trieNode.next[word.charAt(index) - 'a'] != null &&
                        helper(word, index + 1, trieNode.next[word.charAt(index) - 'a']);
            }
        }

        private static class TrieNode {
            boolean isSet = false;
            TrieNode[] next = new TrieNode[26];
        }
    }
}


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");