package com.leetcode;

/**
 * 76. Minimum Window Substring
 * 
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T,
 * return the emtpy string "".
 * If there are multiple such windows, you are guaranteed that there will
 * always be only one unique minimum window in S.
 * 
 * Hint:
 * 用hashmap存所有需要的字符 以及[需要的个数]  用一个count表示还剩的匹配数
 * 用queue存查到符合要求的节点
 * 每找到符合要求的 就出队
 */

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String S, String T) {
    	if (S == null || T == null || T.length() == 0 || S.length() < T.length())
    	    return "";
    	
    	// init map, and set the emerging frequency of each char
        Map<Character, Integer> map = initMap(T);

        int start = 0, remChars2Match = T.length(), finalStart = 0, finalEnd = S.length();

        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) {
                remChars2Match = decMap(map, S.charAt(i), remChars2Match);

                while (remChars2Match == 0 && start <= i) {
                    // mark min substring
                    if (i - start < finalEnd - finalStart) {
                        finalStart = start;
                        finalEnd = i;
                    }

                    if (map.containsKey(S.charAt(start)))
                        remChars2Match = incMap(map, S.charAt(start), remChars2Match);

                    start++;
                }
            }
        }

        return (finalEnd == S.length() || finalStart > finalEnd) ?
                "" : S.substring(finalStart, finalEnd + 1);
    }

    private Map<Character, Integer> initMap(String T) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            Integer count = map.get(T.charAt(i));
            if (count != null)
                map.put(T.charAt(i), count + 1);
            else
                map.put(T.charAt(i), 1);
        }
        return map;
    }

    private int incMap(Map<Character, Integer> map, char ch, int remChars2Match) {
        map.put(ch, map.get(ch) + 1);
        return map.get(ch) > 0 ? remChars2Match + 1 : remChars2Match;
    }

    private int decMap(Map<Character, Integer> map, char ch, int remChars2Match) {
        map.put(ch, map.get(ch) - 1);
        return map.get(ch) >= 0 ? remChars2Match - 1 : remChars2Match;
    }

	public static void main(String[] args) {
		MinimumWindowSubstring t = new MinimumWindowSubstring();
    	//System.out.println(t.minWindow("ADOBECODEBANC", "ABC"));
        //System.out.println(t.minWindow("a", "a"));
        System.out.println(t.minWindow("a", "aa"));
	}

}
