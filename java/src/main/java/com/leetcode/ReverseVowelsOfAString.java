package com.leetcode;

/**
 * 345. Reverse Vowels of a String
 *
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 * Given s = "hello", return "holle".
 *
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 *
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        char[] str = s.toCharArray();
        int i = 0, j = str.length - 1;
        while (i < j) {
            while (i < j && !isVowel(str[i])) ++i;
            while (j > i && !isVowel(str[j])) --j;
            if (i < j) {
                char ch = str[i];
                str[i++] = str[j];
                str[j--] = ch;
            }
        }
        return new String(str);
    }

    private boolean isVowel(char ch) {
        switch (Character.toLowerCase(ch)) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }
}
