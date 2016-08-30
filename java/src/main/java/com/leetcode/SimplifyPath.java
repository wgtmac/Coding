package com.leetcode;

/**
 * 71. Simplify Path
 * 
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * Skill: 
 * 遇到词就存到stack里
 * */

import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";
        Stack<String> stack = new Stack<> ();
        
        int start = 0, i = 0;
        while (i < path.length()) {
            char ch = path.charAt(i);
            if (ch == '/') {
                processWord(path, start, i - 1, stack);
                start = i + 1;
            }
            ++i;
        }
        processWord(path, start, path.length() - 1, stack);
        
        // concat all words in stack
        String ret = "";
        while (!stack.empty()) {
            ret = "/" + stack.pop() + ret;
        }
        
        return ret.isEmpty() ? "/" : ret;
    }

    private void processWord(String path, int start, int end, Stack<String> stack) {
        if (start > end) return;
        String word = path.substring(start, end + 1);
        if (word.equals("..")) {
            if (!stack.empty())
                stack.pop();
        } else if (!word.isEmpty() && !word.equals("."))
            stack.push(word);
    }
}
