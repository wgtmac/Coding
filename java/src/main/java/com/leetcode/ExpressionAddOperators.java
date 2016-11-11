package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 282. Expression Add Operators
 *
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the digits
 * so they evaluate to the target value.
 *
 * Examples:
 *   "123", 6 -> ["1+2+3", "1*2*3"]
 *   "232", 8 -> ["2*3+2", "2+3*2"]
 *   "105", 5 -> ["1*0+5","10-5"]
 *   "00", 0 -> ["0+0", "0-0", "0*0"]
 *   "3456237490", 9191 -> []
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num.length() == 0) return ans;
        char[] expression = new char[num.length() * 2 - 1];
        long n = 0;
        for (int i = 0; i < num.length(); i++) {
            n = n * 10 + num.charAt(i) - '0';
            expression[i] = num.charAt(i);
            helper(ans, expression, i + 1, 0, n, num, i + 1, target);
            if (n == 0) break;
        }
        return ans;
    }

    private void helper(List<String> ans, char[] expression, int pathIdx, long decidedSum,
                        long pendingNum, String num, int numIdx, int target) {
        if (numIdx == num.length()) {
            if (decidedSum + pendingNum == target)
                ans.add(new String(expression, 0, pathIdx));
            return;
        }

        long n = 0;
        for (int i = numIdx, j = pathIdx + 1; i < num.length(); i++) {
            n = n * 10 + num.charAt(i) - '0';
            expression[j++] = num.charAt(i);

            expression[pathIdx] = '+';
            helper(ans, expression, j, decidedSum + pendingNum, n, num, i + 1, target);

            expression[pathIdx] = '-';
            helper(ans, expression, j, decidedSum + pendingNum, -n, num, i + 1, target);

            expression[pathIdx] = '*';
            helper(ans, expression, j, decidedSum, pendingNum * n, num, i + 1, target);

            if (n == 0) break;
        }
    }
}
