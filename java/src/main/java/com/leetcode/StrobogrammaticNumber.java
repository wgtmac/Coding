package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 246. Strobogrammatic Number
 *
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is
 * represented as a string.
 *
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('1', '1');
        map.put('8', '8');
        map.put('0', '0');

        char[] digits = num.toCharArray();
        for (int i = 0, j = digits.length - 1; i <= j; ++i, --j) {
            if (!map.containsKey(digits[i])) return false;
            if (map.get(digits[i]) != digits[j]) return false;
        }

        return true;
    }
}
