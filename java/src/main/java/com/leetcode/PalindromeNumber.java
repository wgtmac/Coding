package com.leetcode;

/**
 * 9. Palindrome Number
 * 
 * DESCRIPTION:
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 * You could also try reversing an integer. 
 * However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. 
 * How would you handle such case?
 * 
 * There is a more generic way of solving this problem.
 * 
 * Skill:
 * 计算位数 直接数字比较
 * 
 */
public class PalindromeNumber {
    
	public boolean isPalindrom(int x) {
		if (x < 0) return false;
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}
		while (x != 0) {
			int l = x / div;
			int r = x % 10;
			if (l != r)
				return false;
			x = (x % div) / 10;
			div /= 100;
		}
		return true;
	}
}
