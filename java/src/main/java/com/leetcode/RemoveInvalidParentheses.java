package com.leetcode;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 *
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 *
 */

/**
 * @link: https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution
 *
 * Explanation:
 * We all know how to check a string of parentheses is valid using a stack.
 * Or even simpler use a counter.
 * The counter will increase when it is ‘(‘ and decrease when it is ‘)’.
 * Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
 *
 * To make the prefix valid, we need to remove a ‘)’. The problem is: which one?
 * The answer is any one in the prefix. However, if we remove any one, we will
 * generate duplicate results, for example: s = ()), we can remove s[1] or s[2]
 * but the result is the same (). Thus, we restrict ourself to remove the first )
 * in a series of concecutive )s.
 *
 * After the removal, the prefix is then valid. We then call the function recursively
 * to solve the rest of the string. However, we need to keep another information:
 * the last removal position. If we do not have this position, we will generate
 * duplicate by removing two ‘)’ in two steps only with a different order.
 * For this, we keep tracking the last removal position and only remove ‘)’ after that.
 *
 * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
 * The answer is: do the same from right to left.
 * However a cleverer idea is: reverse the string and reuse the code!
 * Here is the final implement in Java.
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> list = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<String>() {{ add(s);}};

        if (isValid(s))
            list.add(s);
        else
            queue.offer(s);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String str = queue.poll();

                for (int j = 0; j < str.length(); ++j) {
                    String next = (j == 0 ? "" : str.substring(0, j)) +
                            (j == str.length() - 1 ? "" : str.substring(j + 1));
                    if (set.contains(next)) continue;
                    set.add(next);
                    if (isValid(next)) list.add(next);
                    else queue.offer(next);
                }
            }

            if (!list.isEmpty()) break;
        }

        return list;
    }

    private boolean isValid(String s) {
        int diff = 0;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '(': diff++; break;
                case ')': diff--; break;
            }
            if (diff < 0) return false;
        }
        return diff == 0;
    }

    /**
     * Advanced optimization to de-duplicate
     */
    public List<String> removeInvalidParentheses_Better(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    private void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int count = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) count++;
            if (s.charAt(i) == par[1]) count--;
            if (count >= 0) continue;

            // only proceed when count<0
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }
}
