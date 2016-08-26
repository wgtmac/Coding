package com.leetcode;

/**
 * 29. Divide Two Integers
 * 
 * DESCRIPTION:
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 * 
 * Skill: 
 * 注意绝对值求法 n ^ (n >> 31) - (n >> 31)  或者  ~n + 1 (n < 0)
 * 先对被除数进行展开   2^(n) * b + 2^(n - 1) * b + ..... + b
 * */

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
    	if (divisor == 1 || dividend == 0)
    		return dividend;
    	if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
    		return Integer.MAX_VALUE;
    	
    	long a = Math.abs((long)dividend);
    	long b = Math.abs((long)divisor);
    	
    	if (a < b) return 0;

    	int ret = 0, multi;
    	while (a >= b) {
    		// a >= b guarantees a = b * 2^multi + c
			for (multi = 0; (b << (multi + 1)) <= a; ++multi);
        	ret += (1 << multi);
        	a -= (b << multi);
    	}
    	
    	if (((dividend >> 31) ^ (divisor >> 31)) != 0)
    		ret *= -1;
    	
    	return ret;
    }

    public int divide_brutal(int dividend, int divisor) {
		if (divisor == 1 || dividend == 0)
			return dividend;
		if (divisor == 0)
			return Integer.MAX_VALUE;
		if (dividend == Integer.MIN_VALUE && divisor == -1)
			return Integer.MAX_VALUE;

		long a = Math.abs((long)dividend);
		long b = Math.abs((long)divisor);

		long result = 0;

		while (a >= b) {
			a -= b;
			result++;
		}

		if (((dividend >> 31) ^ (divisor >> 31)) != 0)
			result *= -1;

		return (int)result;
	}
}
