package com.leetcode;

/**
 * 68. Text Justification
 * 
 * Given an array of words and a length L, format the text such that
 * each line has exactly L characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is,
 * pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible. 
 * If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and
 * no extra space is inserted between words.
 * 
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * 
 * Return the formatted lines as:
 * [
 *   "This    is    an",
 *   "example  of text",
 *   "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * 
 * Skill: 
 * 直接写
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        int start = 0, i = 0, currLen = -1;
        while (i < words.length) {
            if (currLen + 1 + words[i].length() > maxWidth) {
                list.add(processLine(words, start, i - 1, maxWidth, currLen));
                start = i;
                currLen = -1;   // first space is always added
            } else
                currLen = currLen + 1 + words[i++].length();
        }

        String lastLine = processLastLine(words, start, words.length - 1, maxWidth);
        list.add(lastLine);

        return list;
    }

    private String processLine(String[] words, int start, int end,
                               int maxWidth, int currLen) {
        char[] line = new char[maxWidth];
        Arrays.fill(line, ' ');

        int wordCount = end - start + 1;
        int totalSpaces = maxWidth - (currLen + 1 - wordCount);

        if (wordCount == 1) {
            System.arraycopy(words[start].toCharArray(), 0, line, 0, words[start].length());
        } else {
            int avgSpaces = totalSpaces / (wordCount - 1);
            int remSpaces = totalSpaces - avgSpaces * (wordCount - 1);

            for (int i = start, j = 0; i <= end; ++i, --remSpaces) {
                System.arraycopy(words[i].toCharArray(), 0, line, j, words[i].length());
                j = j + words[i].length() + avgSpaces;
                if (remSpaces > 0)
                    j += 1;
            }
        }

        return new String(line);
    }

    private String processLastLine(String[] words, int start, int end, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; ++i)
            sb.append(words[i]).append(' ');

        while (sb.length() < maxWidth)
            sb.append(' ');
        return sb.toString().substring(0, maxWidth);
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();

        String[] words = {""};
        System.out.println(tj.fullJustify(words, 0));
    }
}
