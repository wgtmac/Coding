package com.leetcode;

/**
 * 20. Valid Parentheses
 * 
 * DESCRIPTION:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 * 
 * Skill:
 * 用stack暂存，遇到搭配的pop
 */

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        
        char[] ch = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < ch.length; i++) {
            if (!stack.empty() && 
            (stack.peek().charValue() == '(' && ch[i] == ')' ||
            stack.peek().charValue() == '[' && ch[i] == ']' || 
            stack.peek().charValue() == '{' && ch[i] == '}')) {
                stack.pop();
            }
            else {
                stack.push(ch[i]);
            }
        }
        
        return stack.empty();
    }
}
