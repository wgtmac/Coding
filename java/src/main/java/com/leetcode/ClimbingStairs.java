package com.leetcode;

/**
 * 70. Climbing Stairs
 * 
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 *  
 * Skill:
 * DP
 */

public class ClimbingStairs {
	public int climbStairs(int n) {
		if (n <= 0) return 0;
		if (n == 1) return 1;
		else if (n == 2) return 2;

		int[] f = new int[n];
		f[0] = 1;
		f[1] = 2;
		for (int i = 2; i < n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n - 1];
	}
}
