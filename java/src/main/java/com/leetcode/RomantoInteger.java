package com.leetcode;

/**
 * 13. Roman to Integer
 * 
 * DESCRIPTION:
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * Skill: 
 * 比较相邻两个大小即可
 * 后面大 前面的和tmp被减去 
 * 后面小 前面的和被加上
 * 相等则tmp继续加 
 */
import java.util.HashMap;

public class RomantoInteger {
    public int romanToInt(String s) {
    	if (s == null || s.length() == 0) return 0;
   
    	HashMap<Character, Integer> map = new HashMap<>();
    	map.put('I', 1); map.put('V', 5); map.put('X', 10);
    	map.put('L', 50); map.put('C', 100); map.put('D', 500); map.put('M', 1000);
    	
    	int tmp = map.get(s.charAt(0)), num = 0;
        for (int i = 1; i < s.length(); i++) {
        	if (map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
        		num -= tmp;
        		tmp = map.get(s.charAt(i));
        	}
        	else if (map.get(s.charAt(i)) == map.get(s.charAt(i - 1))) {
        		tmp += map.get(s.charAt(i));
        	}
        	else {
        		num += tmp;
        		tmp = map.get(s.charAt(i));
        	}
        }

        num += tmp;
        return num;
    }
}
