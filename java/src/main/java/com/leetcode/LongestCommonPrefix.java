package com.leetcode;

/**
 * 14. Longest Common Prefix
 * 
 * DESCRIPTION:
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * Skill:
 * 分治法去做，两边分别递归去找prefix
 * 
 */
public class LongestCommonPrefix {
    // 1. Method 1, start from the first one, compare prefix with next string, until end; DFS
    // 2. Method 2, start from the first char, compare it with all string, and then the second char; BFS
    // I am using method 1 here
	public String longestCommonPrefix_Better(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int j = 0;
			while (j < strs[i].length() &&
                    j < prefix.length() &&
                    strs[i].charAt(j) == prefix.charAt(j)) {
				j++;
			}
			if (j == 0) {
				return "";
			}
			prefix = prefix.substring(0, j);
		}
		return prefix;
	}
	
	/////////////////////////////////////////////////////
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return recursive(strs, 0, strs.length);
    }
    
    // from start to end - 1
    public String recursive(String[] strs, int start, int end) {
        if (start == end - 1) return strs[start];
        
        int mid = start + (end - start) / 2;
        String left = recursive(strs, start, mid);
        String right = recursive(strs, mid, end);
        
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < Math.min(left.length(), right.length()); i++) {
            if (left.charAt(i) != right.charAt(i)) break;
            prefix.append(left.charAt(i));
        }
        return prefix.toString();
    }
}
