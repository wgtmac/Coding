package com.leetcode;

/**
 * 139. Word Break
 * 
 * Given a string s and a dictionary of words dict,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * Skill: 
 * f[i] 0-i can be broken
 * f[i] = (f[j] == true && keys contains j-i)
 * */

import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0)
            return false;
        
        int len = s.length();
        boolean[] canBeBroken = new boolean[len];
        canBeBroken[0] = dict.contains(s.substring(0, 1));

        for (int i = 1; i < len; i++) {
            if (dict.contains(s.substring(0, i + 1)) ) {
                canBeBroken[i] = true;
            } else {
                for (int j = 0; j < i; j++) {
                    if (canBeBroken[j] && dict.contains(s.substring(j + 1, i + 1))) {
                        canBeBroken[i] = true;
                        break;
                    }
                }
            }
        }
        
        return canBeBroken[len - 1];
    }
}
