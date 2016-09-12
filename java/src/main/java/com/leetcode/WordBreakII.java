package com.leetcode;

/**
 * 140. Word Break II
 * 
 * Given a string s and a dictionary of words dict,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * Skill: 
 * 循坏 从0到i对当前字符串进行dict里查找，找到符合要求的将后面的所有递归，得到的结果与当前substr(0, i + 1)进行合并
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
    private Map<String, List<String>> cache;
    private int maxWordLength = 0;
    
    public List<String> wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0)
            return new ArrayList<>();
        
        cache = new HashMap<>();
        for (String str : dict)
            maxWordLength = Math.max(maxWordLength, str.length());
        helper(s, dict);
        
        return cache.get(s);
    }
    
    private void helper(String s, Set<String> dict) {
        List<String> subStrBreakList = new ArrayList<>();
        
        for (int i = 0; i < Math.min(s.length(), maxWordLength); i++) {
            String word = s.substring(0, i + 1);
            if (dict.contains(word)) {
                if (i + 1 != s.length()) {
                    String remainingString = s.substring(i + 1);
                    if (!cache.containsKey(remainingString))
                        helper(remainingString, dict);
                    
                    for (String brokenStr : cache.get(remainingString))
                        subStrBreakList.add(word + " " + brokenStr);
                } else {
                    subStrBreakList.add(s);
                }
            }
        }
        
        cache.put(s, subStrBreakList);
    }
}
