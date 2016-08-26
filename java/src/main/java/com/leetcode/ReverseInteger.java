package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 7. Reverse Integer
 * 
 * DESCRIPTION:
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * 
 * Have you thought about this?
 * Here are some good questions to ask before coding. 
 * Bonus points for you if you have already thought through this!
 * 
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * 
 * Did you notice that the reversed integer might overflow? 
 * Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. 
 * How should you handle such cases?
 * 
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * 
 * Skill:
 * 如果前9个数倒过来是214748364 一定不会overflow
 * 
 */
public class ReverseInteger {
	int reverse(int x) {
		Queue<Integer> queue = new LinkedList<>();
		boolean positive = x >= 0;
		long num = Math.abs((long)x);

		while (num > 0) {
			queue.offer((int)(num % 10));
			num /= 10;
		}

		assert num == 0;
		while (!queue.isEmpty())
			num = num * 10 + queue.poll();

		if (!positive)
			num *= -1;

		if (Integer.MIN_VALUE <= num && num <= Integer.MAX_VALUE)
			return (int)num;
		else
			return 0;
	}
}
