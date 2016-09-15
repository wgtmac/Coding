package com.leetcode;

/**
 * 150. Evaluate Reverse Polish Notation
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * Skill: 
 * 将遇到的数字全部放入stack内
 * 遇到运算符，pop两个数字进行计算，将结果push回stack
 * 最后stack唯一元素为结果
 * 
 * 判断数字时，因为可能有负号 与减号相同，需要特殊处理
 * */

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        int left, right;
        for (int i = 0; i < tokens.length; i++) {
        	if (Character.isDigit(tokens[i].charAt(tokens[i].length() - 1))) {
        		stk.push(Integer.parseInt(tokens[i]));
        	} else{
    			right = stk.pop();
    			left = stk.pop();

				switch (tokens[i]) {
                    case "+":
                        stk.push(left + right);
                        break;
                    case "-":
                        stk.push(left - right);
                        break;
                    case "*":
                        stk.push(left * right);
                        break;
                    case "/":
                        stk.push(left / right);
                        break;
                }
        	}
        }
        return stk.pop();
    }
}
