package com.leetcode;

import java.util.*;

/**
 * 241. Different Ways to Add Parentheses
 *
 * Given a string of numbers and operators, return all possible results from
 * computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 *
 * Example 1
 * Input: "2-1-1".
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 *
 * Example 2
 * Input: "2*3-4*5"
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 *
 */
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        parse(input, nums, ops);

        List<List<List<Integer>>> record = compute(nums, ops);
        return record.get(0).get(nums.size() - 1);
    }

    private List<List<List<Integer>>> compute(List<Integer> nums, List<Character> ops) {
        List<List<List<Integer>>> record = new ArrayList<>(nums.size());
        for (int i = 0; i < nums.size(); ++i) {
            record.add(i, new ArrayList<>());
            int j = 0;
            for (; j < i; ++j)
                record.get(i).add(Collections.EMPTY_LIST);
            for (; j < nums.size(); ++j)
                record.get(i).add(new ArrayList<>());
            record.get(i).get(i).add(nums.get(i));
        }

        for (int len = 2; len <= nums.size(); ++len) {
            for (int start = 0, end = start + len - 1; end < nums.size(); ++start, ++end) {
                List<Integer> list = record.get(start).get(end);

                for (int k = start; k < end; ++k) {
                    for (int num1 : record.get(start).get(k)) {
                        for (int num2 : record.get(k + 1).get(end)) {
                            list.add(calculate(num1, num2, ops.get(k)));
                        }
                    }
                }
            }
        }

        return record;
    }

    private int calculate(int num1, int num2, char ch) {
        switch (ch) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num1 / num2;
            default: return 0;
        }
    }

    private void parse(String input, List<Integer> nums, List<Character> ops) {
        int num = 0;
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch))
                num = num * 10 + Character.getNumericValue(ch);
            else {
                ops.add(ch);
                nums.add(num);
                num = 0;
            }
        }
        nums.add(num);
    }
}
