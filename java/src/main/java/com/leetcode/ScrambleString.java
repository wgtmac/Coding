package com.leetcode;

/**
 * 87. Scramble String
 * 
 * Given a string s1, we may represent it as a binary tree by
 * partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children,
 * it produces a scrambled string "rgeat".
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at",
 * it produces a scrambled string "rgtae".
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * 
 * Hint:
 * 递归，将字符串每次分割成两份，分割长度为 i和len-i，比较s1和s2按如此长度分割是否符合要求
 * 用数组存放s1和s2分别从某处开始，长度为len的情况是否符合要求
 * traverse[i][j][k] means s1(i~i+k) and s2(j~j+k) are scrambled strings
 * 0: unset    1: false   2 true
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
    	if (s1.length() != s2.length())
    	    return false;
        int len = s1.length();
        // 1D: traverse[i][j][k] means s1(i~i+k) and s2(j~j+k) are scrambled strings
        return helper(s1, s2, 0, 0, len, new Boolean[len][len][len + 1]);
    }
    
    public boolean helper(String s1, String s2, int s1_start, int s2_start,
                          int len, Boolean[][][] isScrambleStr) {
    	if (isScrambleStr[s1_start][s2_start][len] != null)
    	    return isScrambleStr[s1_start][s2_start][len];
    	
    	if (len == 1) {
    		if (s1.charAt(s1_start) == s2.charAt(s2_start))
                isScrambleStr[s1_start][s2_start][1] = Boolean.TRUE;
    		else
                isScrambleStr[s1_start][s2_start][1] = Boolean.FALSE;
    		return isScrambleStr[s1_start][s2_start][1];
    	}
    	
        for (int i = 1; i < len; i++) {
        	if ((helper(s1, s2, s1_start, s2_start, i, isScrambleStr)
                    && helper(s1, s2, s1_start + i, s2_start + i, len - i, isScrambleStr))
                    || (helper(s1, s2, s1_start, s2_start + len - i, i, isScrambleStr)
                    && helper(s1, s2, s1_start + i, s2_start, len - i, isScrambleStr))) {
                isScrambleStr[s1_start][s2_start][len] = Boolean.TRUE;
        		return true;
        	}
        }

        isScrambleStr[s1_start][s2_start][len] = Boolean.FALSE;
    	return false;
    }
    
    public static void main(String[] args) {
    	ScrambleString t = new ScrambleString();
    	System.out.println(t.isScramble("great", "rgtae"));
    }
}
