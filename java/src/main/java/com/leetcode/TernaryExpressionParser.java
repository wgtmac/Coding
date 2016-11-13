package com.leetcode;

/**
 * 439. Ternary Expression Parser
 *
 * Given a string representing arbitrarily nested ternary expressions, calculate
 * the result of the expression. You can always assume that the given expression
 * is valid and only consists of digits 0-9, ?, :, T and F (T and F represent
 * True and False respectively).
 *
 * Note:
 *
 * The length of the given string is ≤ 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most languages).
 * The condition will always be either T or F. That is, the condition will never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T or F.
 * Example 1:
 *
 * Input: "T?2:3"
 * Output: "2"
 * Explanation: If true, then result is 2; otherwise result is 3.
 *
 * Example 2:
 * Input: "F?1:T?4:5"
 * Output: "4"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *
 * "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 * -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 * -> "4"                                    -> "4"
 *
 * Example 3:
 * Input: "T?T?F:5:3"
 * Output: "F"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *
 * "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 * -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 * -> "F"                                    -> "F"
 */
public class TernaryExpressionParser {
    public String parseTernary(String expression) {
        int n = expression.length();
        char[] stack = new char[expression.length()];
        int size = 0;

        for (int i = 0; i < n; ++i) {
            stack[size++] = expression.charAt(i);

            // Note: only when next is not '?' can we merge previous results
            char next = i + 1 == n ? ' ' : expression.charAt(i + 1);
            while (next != '?' && canEval(stack, size)) {
                size = evaluate(stack, size);
            }
        }

        while (size > 1) {
            size = evaluate(stack, size);
        }

        return "" + stack[0];
    }

    private boolean canEval(char[] stack, int size) {
        return size >= 5 && stack[size - 2] == ':' && stack[size - 4] == '?';
    }

    private int evaluate(char[] stack, int size) {
        stack[size - 5] = stack[size - 5] == 'T' ? stack[size - 3] : stack[size - 1];
        return size - 4;
    }
}
