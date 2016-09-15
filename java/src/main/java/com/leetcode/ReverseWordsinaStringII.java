package com.leetcode;

/**
 * 186. Reverse Words in a String II
 * 
 * Given an input string, reverse the string word by word. A word is defined as
 * a sequence of non-space characters. The input string does not contain leading
 * or trailing spaces and the words are always separated by a single space.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Could you do it in-place without allocating extra space?
 * 
 * Skill: 
 * 对每个子字符串进行reverse操作
 * 然后对整个字符串再reverse
 * 中间连续空格不影响，因为每次遇到空格只对前面连续len个字符进行reverse，而此时len为0
 * */
public class ReverseWordsinaStringII {
	public void reverseWords(char[] s) {
        if (s == null)  return; 
        
        for (int i = 0, len = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, i - len, i - 1);
                len = 0;
            } else if (i == s.length - 1)
                reverse(s, i - len, i);
            else
                len++;
        }

        reverse(s, 0, s.length - 1);
	}

    private void reverse(char[] str, int start, int end) {
        char tmp;
        while (start < end) {
            tmp = str[start];
            str[start++] = str[end];
            str[end--] = tmp;
        }
    }
}
