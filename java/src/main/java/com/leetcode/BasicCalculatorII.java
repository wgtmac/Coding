package com.leetcode;

import java.util.*;

/**
 * 227. Basic Calculator II
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators
 * and empty spaces. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 */

public class BasicCalculatorII {
    public int calculate(String s) {
        List<Character> operators = new ArrayList<>();
        Deque<Long> numbers = new LinkedList<>();

        // get numbers and operators
        parse(s, numbers, operators);

        List<Character> opList = new ArrayList<>();

        // 1st pass: process multiplication and division
        processHighPriority(numbers, operators, opList);

        // 2st pass: process addition and subtraction
        return (int)processLowPriority(numbers, opList);
    }

    private long processLowPriority(Deque<Long> numQueue, List<Character> opList) {
        if (!numQueue.isEmpty()) {
            long result = numQueue.poll();
            for (char op : opList) {
                result = calculate(result, numQueue.poll(), op);
            }
            return result;
        }
        return 0l;
    }

    private void processHighPriority(Deque<Long> numbers, List<Character> operators,
                                     List<Character> opList) {
        if (!numbers.isEmpty()) {
            int size = numbers.size();
            long lNum = numbers.poll(), rNum;

            for (int i = 1; i < size; ++i) {
                rNum = numbers.poll();
                char op = operators.get(i - 1);
                switch (op){
                    case '+':
                    case '-':
                        numbers.offer(lNum);
                        opList.add(op);
                        lNum = rNum;
                        break;
                    case '*':
                    case '/':
                        lNum = calculate(lNum, rNum, op);
                        break;
                }
            }
            numbers.offer(lNum);
        }
    }

    private void parse(String s, Deque<Long> numbers, List<Character> operators) {
        long num = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)){
                num = num == -1 ? (ch - '0') : (num * 10 + (ch - '0'));
            } else {
                if (num != -1) {
                    numbers.offer(num);
                    num = -1;
                }

                if (!Character.isSpaceChar(ch))
                    operators.add(ch);
            }
        }

        if (num != -1)
            numbers.offer(num);
    }

    private long calculate (long a, long b, char op) {
        switch(op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: return 0l;
        }
    }
}