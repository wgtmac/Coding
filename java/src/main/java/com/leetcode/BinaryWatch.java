package com.leetcode;

import java.util.*;

/**
 * 401. Binary Watch
 *
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the
 * 6 LEDs on the bottom represent the minutes (0-59).
 *
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 * For example, the above binary watch reads "3:25".
 *
 * Given a non-negative integer n which represents the number of LEDs that are
 * currently on, return all possible times the watch could represent.
 *
 * Example:
 *
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *
 * Note:
 *   - The order of output does not matter.
 *   - The hour must not contain a leading zero, for example "01:00" is not valid,
 *     it should be "1:00".
 *   - The minute must be consist of two digits and may contain a leading zero,
 *     for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new ArrayList<>();
        if (num < 0 || num > 10) return ans;

        Map<Integer, List<String>> hrs = constructMap(12, false);
        Map<Integer, List<String>> mins = constructMap(60, true);

        for (int h = Math.max(num - 5, 0), m = num - h; h <= Math.min(3, num); ++h, --m) {
            for (String hr : hrs.get(h)) {
                for (String min : mins.get(m)) {
                    ans.add(hr + ":" + min);
                }
            }
        }
        return ans;
    }

    private Map<Integer, List<String>> constructMap(int max, boolean fixed2Digits) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < max; ++i) {
            int bits = getBits(i);
            List<String> list = map.get(bits);
            if (list == null) {
                list = new LinkedList<>();
                map.put(bits, list);
            }

            String num = Integer.toString(i);
            if (fixed2Digits && num.length() == 1)
                num = '0' + num;
            list.add(num);
        }
        return map;
    }

    private int getBits(int num) {
        int bits = 0;
        while (num != 0) {
            bits++;
            num &= (num - 1);
        }
        return bits;
    }
}
