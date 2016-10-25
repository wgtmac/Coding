package com.leetcode;

import java.util.*;

/**
 * 316. Remove Duplicate Letters
 *
 * Given a string which contains only lowercase letters, remove duplicate letters
 * so that every letter appear once and only once. You must make sure your result
 * is the smallest in lexicographical order among all possible results.
 *
 * Example:
 *
 * Given "bcabc"
 * Return "abc"
 *
 * Given "cbacdcbc"
 * Return "acdb"
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // put chars in stack as increasing as possible
        Stack<Character> stack = new Stack<>();
        Set<Character> inStack = new HashSet<>();

        // get char count
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (inStack.contains(ch)) {
                // already processed this char in the front
                map.put(ch, map.get(ch) - 1);
            } else if (stack.isEmpty() || stack.peek() < ch) {
                stack.push(ch);
                inStack.add(ch);
                map.put(ch, map.get(ch) - 1);
            } else {
                // pop all bigger front chars when they have remaining quotes
                while (!stack.isEmpty() && stack.peek() >= ch && map.get(stack.peek()) > 0) {
                    inStack.remove(stack.pop());
                }
                stack.push(ch);
                inStack.add(ch);
                map.put(ch, map.get(ch) - 1);
            }
        }

        StringBuilder sb = new StringBuilder(stack.size());
        for (char ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters r = new RemoveDuplicateLetters();
        System.out.println(r.removeDuplicateLetters("cbacdcbc"));
    }
}
