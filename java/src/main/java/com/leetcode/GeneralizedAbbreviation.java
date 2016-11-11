package com.leetcode;

import java.util.*;

/**
 * 320. Generalized Abbreviation
 *
 * Write a function to generate the generalized abbreviations of a word.
 *
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1",
 * "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class GeneralizedAbbreviation {
    // divide and conquer
    public List<String> generateAbbreviations(String word) {
        if (word == null || word.isEmpty()) {
            return new ArrayList<String>() {{ add(""); }};
        }
        if (word.length() == 1) {
            return new ArrayList<String>() {{ add("1"); add(word); }};
        }

        int mid = word.length() / 2;
        List<String> lLists = generateAbbreviations(word.substring(0, mid));
        List<String> rLists = generateAbbreviations(word.substring(mid));

        Set<String> set = new HashSet<>();
        for (String left : lLists) {
            for (String right : rLists) {

                if (!Character.isDigit(left.charAt(left.length() - 1)) ||
                        !Character.isDigit(right.charAt(0))) {
                    set.add(left + right);
                } else {
                    int l = left.length() - 1;
                    while (l >= 0 && Character.isDigit(left.charAt(l))) --l;
                    String prefix = (l >= 0 ? left.substring(0, l + 1) : "");

                    int r = 0;
                    while (r < right.length() && Character.isDigit(right.charAt(r))) ++r;
                    String suffix = (r < right.length() ? right.substring(r): "");

                    int num = Integer.valueOf(left.substring(l + 1)) +
                            Integer.valueOf(right.substring(0, r));

                    set.add(prefix + num + suffix);
                }
            }
        }

        return new ArrayList<>(set);
    }
}
