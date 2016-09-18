package com.leetcode;

/**
 * 172. Factorial Trailing Zeroes
 * 
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * Skill: 
 * 数5的个数
 * */ 
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
    	int sum = 0;
    	for (long i = 5; i <= n; i *= 5) {
    		sum += (n / i);
    	}
		return sum;
    }
    
    public static void main(String[] args) {
    	FactorialTrailingZeroes t = new FactorialTrailingZeroes();
    	System.out.println(t.trailingZeroes(5));
    }
}
