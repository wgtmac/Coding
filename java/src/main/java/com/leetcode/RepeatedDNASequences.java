package com.leetcode;

/**
 * 187. Repeated DNA Sequences
 * 
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA. Write a function to find all the
 * 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * 
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * Skill:
 * 用hashmap保存出现的每个字符串
 * 如果直接存字符串 memory会超 因此把ACGT分别当二进制00 01 10 11
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<> ();
        if (s == null || s.length() < 10) return ret;
        
        Map<Integer, Boolean> map = new HashMap<>();
        
        int num = 0;
        for (int i = 0; i < 9; i++) {
        	num <<= 2;
        	num |= getBits(s.charAt(i));
        }
        
        for (int i = 9; i < s.length(); i++) {
        	num <<= 2;
        	num &= 0xFFFFF;  // cut off first 12 bits to all 0s
        	num |= getBits(s.charAt(i));
        	
            if (!map.containsKey(num)) {
                map.put(num, true);
            } else {
            	if (map.get(num)) {
            		ret.add(s.substring(i - 9, i + 1));
            		map.put(num, false);
            	}
            }
        }
        
        return ret;
    }

    private int getBits(char ch) {
        switch (ch) {
            case 'A': return 0x0;
            case 'C': return 0x1;
            case 'G': return 0x2;
            case 'T': return 0x3;
        }
        return -1;
    }

    public static void main(String[] args) {
        RepeatedDNASequences t = new RepeatedDNASequences();
        System.out.println(t.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

}
