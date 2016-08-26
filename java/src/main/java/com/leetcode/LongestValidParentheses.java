package com.leetcode;

/**
 * 32. Longest Valid Parentheses
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 *
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * 
 * Skill:
 * trick: 用一个stack存符号，(进，)出。 再用一个stack存入栈的序号 这样每次配对的可以标记下位置 
 * 
 * 
 */

import java.util.Stack;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        
        boolean[] isValid = new boolean[s.length()];
        Stack<Character> stack = new Stack<>();
        Stack<Integer> posStack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (!stack.empty() && stack.peek() == '(' && s.charAt(i) == ')') {
                stack.pop();
                isValid[posStack.pop()] = true;
                isValid[i] = true;
            }
            else {
                stack.push(s.charAt(i));
                posStack.push(i);
            }
        }
        
        int max = 0, cnt = 0;
        for (int i = 0; i < isValid.length; i++) {
            if (isValid[i]) cnt++;
            else cnt = 0;
            max = Math.max(max, cnt);
        }

        return max;
    }
    
    //////////////////////////////////////////////// ONLY ONE STACK STORING INDEX
    public int longestValidParentheses_Better (String s) {
        if (s == null) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int maxLen = 0;
        int accumulatedLen = 0;

        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    accumulatedLen = 0;
                } else {
                    int matchedPos = stack.pop();
                    int matchedLen = i - matchedPos + 1;

                    if (stack.isEmpty()) {
                        accumulatedLen += matchedLen;
                        matchedLen = accumulatedLen;
                    } else {
                        matchedLen = i - stack.peek();
                    }

                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }

        return maxLen;
   }
}
