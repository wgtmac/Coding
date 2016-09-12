package com.leetcode;

/**
 * 127. Word Ladder
 * 
 * Given two words (start and end), and a dictionary,
 * find the length of shortest transformation sequence from start to end, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * Skill: 
 * BFS
 * 用一个队列
 * 把当前的字符串每一位用a-z去尝试替换，字典里存在则存入queue
 * 
 * 最早的一定最先达到
 * */

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	if (beginWord == null || endWord == null || wordList == null) return 0;
        
        Queue<String> queue = new LinkedList<>();
     
        int ladderLength = 1;
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	ladderLength++;
        	for (int i = 0; i < size; i++) {
        		String currStr = queue.poll();
        		for (char ch = 'a'; ch <= 'z'; ch++) {
        			for (int j = 0; j < currStr.length(); j++) {

        			    // time optimization, avoid self-cycle
        			    if (ch == currStr.charAt(j)) continue;
        			    
        				String nextStr = currStr.substring(0, j) + ch + currStr.substring(j + 1);
        				if (nextStr.equals(endWord)) {
        					return ladderLength;
        				}	
        				if (wordList.contains(nextStr)) {
        					queue.offer(nextStr);

                            // time optimization, the word in the dict is reached
        					wordList.remove(nextStr);
        				}
        			}
        		}
        	}
        }
        
    	return 0;
    }

    public int ladderLength_BiBFS(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) return 0;

        Set<String> fQ = new LinkedHashSet<>(), bQ = new LinkedHashSet<>();

        int ladderLength = 1;
        fQ.add(beginWord);
        bQ.add(endWord);

        while (!fQ.isEmpty() && !bQ.isEmpty()) {
            ladderLength++;
            if (updateQueue(fQ, bQ, wordList))
                return ladderLength;

            ladderLength++;
            if (updateQueue(bQ, fQ, wordList))
                return ladderLength;
        }

        return 0;
    }

    private boolean updateQueue(Set<String> runningQ, Set<String> queryQ, Set<String> wordList) {
        int sizeOfCurrLevel = runningQ.size();

        while (sizeOfCurrLevel-- > 0) {
            char[] word = runningQ.iterator().next().toCharArray();

            for (int i = 0; i < word.length; ++i) {
                char backup = word[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (backup == ch) continue;
                    word[i] = ch;
                    String nextWord = new String(word);

                    if (queryQ.contains(nextWord))
                        return true;

                    if (wordList.contains(nextWord))
                        runningQ.add(nextWord);
                }
                word[i] = backup;
            }
        }

        wordList.removeAll(queryQ);
        return false;
    }
}
