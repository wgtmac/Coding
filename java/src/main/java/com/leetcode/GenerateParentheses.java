package com.leetcode;

/**
 * 22. Generate Parentheses
 *
 * Given n pairs of parentheses,
 * write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 
 * Skill:
 * 用Hash去重, 递归产生序列
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
    	HashSet<String> set = new HashSet<> ();
        helper ("", n, 0, set);
        return new ArrayList<>(set);
    }
    
    void helper (String str, int l, int r, HashSet<String> set) {
    	if(l == 0 && r == 0) {
    		set.add(str);
    		return;
    	}
    	
    	if (l > 0) helper (str + '(', l - 1, r + 1, set);
    	if (r > 0) helper (str + ')', l, r - 1, set);
    }
}
