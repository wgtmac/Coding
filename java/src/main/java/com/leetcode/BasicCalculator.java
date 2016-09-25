package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 224. Basic Calculator
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces.
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
*/

public class BasicCalculator {
    public int calculate(String s) {
    	List<String> exp = new ArrayList<String>();
    	s += " ";
        char[] str = s.toCharArray();
        String num = "";
        for (int i = 0; i < str.length; ++i) {
        	if (Character.isDigit(str[i]))
        		num += str[i];
        	else {
        		if (num.length() > 0) {
        			exp.add(new String(num));
        			num = "";
        		}
        		if (str[i] != ' ') 
        			exp.add("" + str[i]);
			}
        }

        Stack<String> S = new Stack<>();
        Stack<Integer> rpn = new Stack<>();
        for (String ss : exp) {
        	if (Character.isDigit(ss.charAt(0))) 
        		rpn.push(Integer.parseInt(ss));
        	else if (ss.equals("("))
        		S.push(ss);
        	else if (ss.equals(")")){
        		while (!S.peek().equals("(")) {
        			rpn.push(calculate(rpn.pop(), rpn.pop(), S.pop()));
        		}
        		S.pop();
        	}
        	else {
        		while (!S.empty() && !S.peek().equals("(")) {
        			rpn.push(calculate(rpn.pop(), rpn.pop(), S.pop()));
        		}
        		S.push(ss);
        	}
        }
        while (!S.empty())
        	rpn.push(calculate(rpn.pop(), rpn.pop(), S.pop()));
        return rpn.empty() ? 0 : rpn.peek();
    }

    private int calculate (int b, int a, String op) {
    	switch(op) {
        	case "+": return a + b;
        	case "-": return a - b;
     		case "*": return a * b;
			case "/": return a / b;
			default: return 0;
    	}
	}
}