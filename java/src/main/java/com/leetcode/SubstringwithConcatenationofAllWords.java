package com.leetcode;

/**
 * 30. Substring with Concatenation of All Words
 *
 * You are given a string, S, and a currList of words, L,
 * that are all of the same length.
 *
 * Find all starting indices of substring(s) in S
 * that is a concatenation of each word in L exactly once and without any intervening characters.
 *
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 * Skill: 
 * 每个可能的位置进行寻找
 * 就是暴力法
 * 用hashmap存每个dict里的词还差多少次匹配
 */

import java.util.*;

public class SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String S, String[] L) {
    	List<Integer> list = new ArrayList<> ();
    	if (S == null || L == null || L.length == 0)
    	    return list;
    	
    	Map<String, Integer> wordMap = new HashMap<>();
        for (String str : L) {
            Integer cnt = wordMap.get(str);
        	if (cnt != null)
        		wordMap.put(str, cnt + 1);
        	else
        		wordMap.put(str, 1);
        }
        
        int count = L.length;
        int wordLen = L[0].length();
        int subStrTotalLen = count * wordLen;
        
        for (int i = 0; i <= S.length() - subStrTotalLen; i++) {
			Map<String, Integer> map = new HashMap<>(wordMap);
			int matchedCount = 0;
        	for (int j = i; j < i + subStrTotalLen; j += wordLen ) {
        		String str = S.substring(j, j + wordLen);
                Integer remCnt = map.get(str);
                if (remCnt != null && remCnt > 0)  {
                    map.put(str, remCnt - 1);
                    matchedCount++;
                } else {
                    break;
                }
        	}
        	
        	if (matchedCount == count) list.add(i);
        }
    	
    	return list;
    }
}
