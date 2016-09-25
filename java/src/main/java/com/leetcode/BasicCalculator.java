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
 *
 * Hint: + and - do not care about order
 */

public class BasicCalculator {

    public int calculate(String s) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        int num = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num == -1 ? (ch - '0') : (num * 10 + ch - '0');
            } else {
                if (num != -1) {
                    numbers.add(num);
                    num = -1;
                }

                if (ch == '+' || ch == '-') {
                    operators.add(ch);
                } else if (!Character.isSpaceChar(ch)) {   // ch == '('
                    int start = ++i;
                    for (int lParenCnt = 1, rParenCnt = 0; lParenCnt != rParenCnt; ++i) {
                        if (s.charAt(i) == '(')
                            lParenCnt++;
                        else if (s.charAt(i) == ')')
                            rParenCnt++;
                    }
                    numbers.add(calculate(s.substring(start, --i)));
                }
            }
        }

        // very easy to ignore this
        if (num != -1) numbers.add(num);

        int result = 0;
        if (!numbers.isEmpty()) {
            result = numbers.get(0);
            for (int i = 1; i < numbers.size(); ++i) {
                result = eval(result, numbers.get(i), operators.get(i - 1));
            }
        }
        return result;
    }

    private int eval (int a, int b, char op) {
        switch(op) {
            case '+': return a + b;
            case '-': return a - b;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        BasicCalculator s = new BasicCalculator();
        System.out.println(s.calculate("1") == s.calculate_using_stack("1"));
        System.out.println(s.calculate("1+1") == s.calculate_using_stack("1+1"));
        System.out.println(s.calculate("1+1+1") == s.calculate_using_stack("1+1+1"));
        System.out.println(s.calculate("1+(1+1)") == s.calculate_using_stack("1+(1+1)"));
        System.out.println(s.calculate("1+(1-1)") == s.calculate_using_stack("1+(1-1)"));
        System.out.println(s.calculate("  1+(1 -(1 - 1))") == s.calculate_using_stack("  1+(1 -(1 - 1))"));
    }

    /**
     * Use stack to hold numbers & operators to be processed
     * When meeting a operator +/-, all previous expressions should evaluate;
     * When meeting a ), evaluate all expressions belong to a parenthesis pair.
     */
    public int calculate_using_stack(String s) {
    	List<String> symbolList = new ArrayList<>();
    	s += " ";
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
        	if (Character.isDigit(ch))
        		num.append(ch);
        	else {
        		if (num.length() > 0) {
        			symbolList.add(num.toString());
                    num.delete(0, num.length());
        		}
        		if (!Character.isSpaceChar(ch))
        			symbolList.add(Character.toString(ch));
			}
        }

        Stack<String> operatorStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();
        for (String symbol : symbolList) {
        	if (Character.isDigit(symbol.charAt(0)))
        		numberStack.push(Integer.parseInt(symbol));
        	else if (symbol.equals("("))
        		operatorStack.push(symbol);
        	else if (symbol.equals(")")){
        	    // calculate nearest () pair to a single number
        		while (!operatorStack.peek().equals("(")) {
        			numberStack.push(calculate(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
        		}
        		operatorStack.pop(); // remove '('
        	} else {
        	    // when there is a '+/-', expressions on the left should evaluate
                // to a single number
        		while (!operatorStack.empty() && !operatorStack.peek().equals("(")) {
        			numberStack.push(calculate(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
        		}
        		operatorStack.push(symbol);
        	}
        }
        while (!operatorStack.empty())
        	numberStack.push(calculate(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
        return numberStack.empty() ? 0 : numberStack.peek();
    }

    private int calculate (int b, int a, String op) {
    	switch(op) {
        	case "+": return a + b;
        	case "-": return a - b;
			default: return 0;
    	}
	}
}