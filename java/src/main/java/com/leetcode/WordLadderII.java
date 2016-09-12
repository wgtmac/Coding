package com.leetcode;

/**
 * 126. Word Ladder II
 * 
 * Given two words (start and end), and a dictionary,
 * find all shortest transformation sequence(s) from start to end, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 * [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * Skill: 
 * BFS
 * 用一个队列
 * 把当前的字符串每一位用a-z去尝试替换，字典里存在则存入queue
 * 
 * 每个当前路径点 拷贝前一个点的path 并加上当前的
 * 注意，因为可能两个不同路径经过相同跳数到当前位置，所以每一个可能的path是一个list，再用一个list去保存这些list
 * 由于可能第二层有A和B, 但第二层的A可以到B,此时B还没有从dict里面删去，因此需要判断已存的B的路径长度是不是跟A一样长 一样长表示是上层未处理的 不考虑
 * */
import java.util.*;

public class WordLadderII {
	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		List<List<String>> ret = new ArrayList<>();
		if (beginWord == null || endWord == null || wordList == null)
		    return ret;

		if (beginWord.equals(endWord)) {
			ret.add(new ArrayList<String>() {{ add(beginWord); add(endWord);}});
			return ret;
		}
		

		// all possible paths to the KEY word
		Map<String, List<List<String>>> path2Word = new HashMap<>();
		path2Word.put(beginWord, new ArrayList<List<String>>() {{
		    add(new ArrayList<String>() {{add(beginWord);}});}});

        Queue<String> queue = new LinkedList<> ();
        queue.offer(beginWord);

		// BFS
		boolean isFound = false;
		while (!isFound && !queue.isEmpty()) {
			// BFS
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String currWord = queue.poll();
				
				for (char ch = 'a'; ch <= 'z'; ch++) {
					for (int j = 0; j < currWord.length(); j++) {
                        if (ch == currWord.charAt(j)) continue;

						String nextWord = currWord.substring(0, j) + ch + currWord.substring(j + 1);

						if (nextWord.equals(endWord)) {
							isFound = true;
							for (List<String> lastList : path2Word.get(currWord))
								ret.add(new ArrayList<String>(lastList) {{add(endWord);}});
						} else if (wordList.contains(nextWord)) {
							/**
                             * there exists a shorter path from beginWord to nextWord
                             */
							if (path2Word.containsKey(nextWord) &&
                                    !path2Word.get(nextWord).isEmpty() &&
                                    path2Word.get(nextWord).get(0).size() <
                                            path2Word.get(currWord).get(0).size() + 1) {
							    continue;
                            }

                            if (!path2Word.containsKey(nextWord)) {
                                path2Word.put(nextWord, new ArrayList<>());
                                queue.offer(nextWord);
                            }

							// get every possible path to current string
							for (List<String> lastList : path2Word.get(currWord))
								path2Word.get(nextWord).add(new ArrayList<String>(lastList){{add(nextWord);}});
						}
					}
				}

                // this optimization is very significant
				wordList.remove(currWord);
			}
		}
		
		return ret;
	}
}
