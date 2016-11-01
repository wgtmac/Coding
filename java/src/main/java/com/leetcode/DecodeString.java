package com.leetcode;


/**
 * 394. Decode String
 *
 * Given an encoded string, return it's decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits
 * and that digits are only for those repeat numbers, k. For example, there won't
 * be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();

        int num = 0, left = 0, right = 0;
        boolean metBracket = false;
        for (int i = 0, start = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                if (!metBracket) {    // note here
                    num = num * 10 + (ch - '0');
                    start = i + 2;    // skip digit and '['
                }
            } else if (ch == '[') {
                metBracket = true;
                left++;
            } else if (ch == ']') {
                right++;
                if (left == right) {
                    String nested = decodeString(s.substring(start, i));

                    while (num-- > 0) sb.append(nested);
                    // reset bracket counters; num will be cleared above
                    metBracket = false;
                    left = right = num = 0;
                }
            } else {
                if (!metBracket) sb.append(ch);
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString d = new DecodeString();
        //System.out.println(d.decodeString("3[a]2[bc]"));
        System.out.println(d.decodeString("3[a2[c]]"));
        //System.out.println(d.decodeString("2[abc]3[cd]ef"));
    }
}
