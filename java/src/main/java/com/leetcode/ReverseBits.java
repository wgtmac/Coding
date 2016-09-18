package com.leetcode;

/**
 * 190. Reverse Bits
 * 
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 *  
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * Related problem: Reverse Integer
 * 
 * Skill: 
 * 直接reverse
 * */

import java.util.HashMap;
import java.util.Map;

public class ReverseBits {
    // you need treat n as an unsigned value
	Map<Integer, Integer> map = new HashMap<>();
	
    public int reverseBits(int n) {
        if (!map.containsKey(n)) {
        	int rev = 0;
        	for (int i = 0; i != 32; ++i) {
        		rev |= ((n >> i) & 1) << (31 - i);
        	}
        	map.put(n, rev);
        }
        
        return map.get(n);
    }
   

}
