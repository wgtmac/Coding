package com.leetcode;

/**
 * 131. Palindrome Partitioning
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * 
 * Skill: 
 * 递归，如果前面到j可以分割，去递归j以后的再组合
 * */

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    private List<List<String>> result;
    
    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        helper(s, new ArrayList<> ());
        return result;
    }
    
	void helper(String s, List<String> list) {
        if (s.length() == 0) {
            result.add((List<String>)((ArrayList<String>)list).clone());
            return;
        }
        
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(0, i + 1);
            if (isPalindrome(str)) {
                list.add(str);
                helper(s.substring(i + 1), list);
                list.remove(list.size() - 1);
            }
        }
    }
    
    boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }
}
