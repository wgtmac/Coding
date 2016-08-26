package com.leetcode;

/**
 * 12. Integer to Roman
 * 
 * DESCRIPTION:
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * Skill: 
 * 每个位置计算方法相同 仿照 I V X 即可
 * 阿拉伯数字每一位都有对应规律 0-9按IVX来构造
 */

public class IntegertoRoman {
	public String intToRoman(int num) {
		int four = num / 1000;
		int three = num / 100 % 10;
		int two = num / 10 % 10;
		int one = num % 10;
		
		StringBuilder str = new StringBuilder();
		
		while (four-- != 0)
			str.append('M');
		
		str.append(set('C', 'D', 'M', three));
		str.append(set('X', 'L', 'C', two));
		str.append(set('I', 'V', 'X', one));
		
		return str.toString();
	}
	
	public String set(char I, char V, char X, int num) {
		StringBuilder str = new StringBuilder();
		if (num == 9) {
			str.append(I).append(X);
		} else if (num >= 5) {
			num -= 5;
			str.append(V);
			while (num-- != 0) str.append(I);
		} else if (num == 4) {
			str.append(I).append(V);
		} else {
			while (num-- != 0) str.append(I);
		}
		return str.toString();
	}
}
