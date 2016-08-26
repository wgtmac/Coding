package com.leetcode;
/**
 * 8. String to Integer (atoi)
 * 
 * DESCRIPTION:
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: Carefully consider all possible input cases. 
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
 * You are responsible to gather all the input requirements up front.
 * 
 * Requirements for atoi:
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned. 
 * If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 * 
 * Skill:
 * 计算位数 直接数字比较
 * 
 * Tip：别考虑复杂  直接trim，然后多余++ --不考虑
 * 只看前10位数字，可比较前九位与MAX/10
 * 
 */

public class StringtoIntegeratoi {
	public int atoi (String str) {
		int i = 0;
		// space expected
		for (; i < str.length() && Character.isSpaceChar(str.charAt(i)); ++i);

		// +/- expected
		int sign = 1;
		if (i < str.length()) {
			switch (str.charAt(i)) {
				case '-': sign = -1;
				case '+': ++i;
				default: // do nothing
			}
		}

		int len = 0;
		long num = 0;
		while (len < 10 && i < str.length() &&
				Character.isDigit(str.charAt(i))) {
			num = num * 10 + Character.getNumericValue(str.charAt(i++));
			len++;
		}

		num *= sign;
		if (len == 10) {
			if (i < str.length() && Character.isDigit(str.charAt(i)))
				num = sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			else if (num >= Integer.MAX_VALUE)
				num = Integer.MAX_VALUE;
			else if (num <= Integer.MIN_VALUE)
				num = Integer.MIN_VALUE;
		}

		return (int)num;
	}
}
