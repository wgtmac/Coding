package com.leetcode;

/**
 * 44. Wildcard Matching
 * 
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * 
 * Skill: 
 * 
 * 记下上一次出现star的匹配i和j，回溯
 * 
 * ?和正常字符都一一匹配
 * 需要注意：*可以匹配到任何字符，也就是说，如果遇到 *xxxx*,则可以认为，只要前一个*后面找到了xxxx，则以后回溯不需要考虑第一个*
 * 
 * xxxxxxABCDxxxxxxABCDxxxxxxxxxxxxxxABCDxxxxE
 *    *ABCD*E 其实xxxxxxABCDxxxxxxxxxxxxxxABCDxxxx就可以满足后面的*
 * 
 * 注意循环条件
 * 
 * 对S每个字符 去尽力匹配，当前遇到星号，如果能匹配到下一个星号之前所有内容，则匹配后停止，否则继续往S后面匹配
 * 常规思路：http://blog.csdn.net/tingmei/article/details/8049926
 * 
 * DP:     http://blog.csdn.net/linhuanmars/article/details/21198049
 * 思路：res表示当前p[j]能匹配到的最远处 不是*时需要倒着赋值
 * (1)p[j]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样
 *    （如果有p在j上的字符是'?'，也是相同），并且res[i]==true，则更新res[i+1]为true，
 *     否则res[i+1]=false;
 * (2)p[j]是'*'。因为'*'可以匹配任意字符串，所以在前面的res[i]只要有true，那么剩下的
 *    res[i+1], res[i+2],...,res[s.length()]就都是true了。
 */

public class WildcardMatching {
	public boolean isMatch(String s, String p) {
		int i = 0;
		int j = 0;
		int prevStarIndex = -1;	      // 上一个*的位置
		int starMatchedIndex = -1;	  // 标记遇到*时，待匹配的字符
		while (i < s.length()) {
			if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				++i;
				++j;
			} else if (j < p.length() && p.charAt(j) == '*') {		// 找到下一个非*字符
				prevStarIndex = j++;
				starMatchedIndex = i;
			} else if (prevStarIndex != -1) {
                // j == p.length() OR s[i] != p[j]
                // 有*被跳过，则跳回最近的那个*
				j = prevStarIndex + 1;
				i = ++starMatchedIndex;
			} else {
				return false;
			}
		}
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		return j == p.length();
	}

}
