package com.leetcode;

/**
 * 248. Strobogrammatic Number III
 *
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 *
 * Write a function to count the total strobogrammatic numbers that exist in the
 * range of low <= num <= high.
 *
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three
 * strobogrammatic numbers.
 *
 * Note:
 * Because the range might be a large number, the low and high numbers are
 * represented as string.
 */
public class StrobogrammaticNumberIII {
    public int strobogrammaticInRange(String low, String high) {
        int[] count = {0};
        for(int len = low.length(); len <= high.length(); len++) {
            if (len == low.length() || len == high.length()) {
                if (len % 2 == 0) {
                    helper(low, high, "0", len, count);
                    helper(low, high, "1", len, count);
                    helper(low, high, "8", len, count);
                } else {
                    helper(low, high, "", len, count);
                }
            } else {
                count[0] += computeCount(len);
            }
        }
        return count[0];
    }

    private int computeCount(int len) {
        if (len == 1) return 2;
        if (len == 2) return 4;
        int count = 4;
        while ((len -= 2) > 1) count *= 5;
        if (len == 1) count *= 3;
        return count;
    }

    private void helper(String low, String high, String str, int len, int[] count) {
        if (str.length() > len) return;
        if (str.length() == len) {
            if (len != 1 && str.charAt(0) == '0') return;
            if((str.length() == low.length() && str.compareTo(low) < 0) ||
                    (str.length() == high.length() && str.compareTo(high) > 0)) return;
            count[0]++;
            return;
        }
        helper(low, high, "0" + str + "0", len, count);
        helper(low, high, "1" + str + "1", len, count);
        helper(low, high, "8" + str + "8", len, count);
        helper(low, high, "6" + str + "9", len, count);
        helper(low, high, "9" + str + "6", len, count);
    }
}
