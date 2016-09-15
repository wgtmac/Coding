package com.leetcode;

/**
 * 137. Single Number II
 * 
 * Given an array of integers, every element appears three times except for one.
 * Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 * 
 * Skill: 
 * 先二进制化，然后做三进制不进位加法
 * 
 * 牛逼解法：模仿三进制不进位加法
 * two代表高位，one代表地位
 * 遇到1， 00 -> 01    01 -> 10    10 -> 00
 * 所以two : 只有two和one不同时(two^one)，遇到1需要变，遇到0不变 (&x)
 * one，只有two为0时(~two) 遇到1变遇到0不变(&x)
 * */

public class SingleNumberII {
    public int singleNumber(int[] A) {
        if (A == null || A.length == 0) return 0;
        
        int[] bits = new int[32];
        int result = 0;
        
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < 32; j++)
                bits[j] = (bits[j] + ((A[i] >> (31 - j)) & 1)) % 3;

        for (int j = 0; j < 32; j++)
            result += bits[j] << (31 - j);
        
        return result;
    }

    /**
     *         0   1
     * 00 ->  00  01
     * 01 ->  01  10
     * 10 ->  10  00
     * */
    public int singleNum (int[] A) {
    	int one = 0, two = 0;
    	
    	for (int x : A) {
    		int t1 = one, t2 = two;
    	    two ^= ((t1 ^ t2) & x);
    	    one ^= (~t2) & x;
    	}
    	
    	return one;    	

    }
}
