package com.leetcode;

/**
 * 161. One Edit Distance
 * 
 * Given two strings S and T, determine if they are both one edit distance apart.
 * 
 * Skill:
 * 比edit distance简单多
 * 直接比较长度相等和差1两种情况
 */

public class OneEditDistance {
    private boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) > 1) return false;
        
        if (s.length() == t.length()) {
        	boolean isEditHappened = false;
        	for (int i = 0; i < s.length(); i++) {
    			if (s.charAt(i) != t.charAt(i)) {
    				if (isEditHappened) return false;
    				else isEditHappened = true;
    			}
        	}
        	return isEditHappened;
        } else {
        	String longer = s.length() > t.length() ? s : t;
        	String shorter = longer == s ? t : s;
        	
        	for (int i = 0; i < longer.length(); i++) {
        		if (shorter.equals(longer.substring(0, i) + longer.substring(i + 1))) return true;
        	}
        	return false;
        }
    }
}
