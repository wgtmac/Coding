package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 247. Strobogrammatic Number II
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 */
public class StrobogrammaticNumberII {
    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('1', '1');
        map.put('8', '8');
        map.put('0', '0');

        List<String> list = new ArrayList<>();
        helper(map, "", "", n, list);
        return list;
    }

    private void helper(Map<Character, Character> map, String left,
                          String right, int n, List<String> list) {
        if (n == 0) {
            list.add(left + right);
        } else if (n == 1) {
            list.add(left + '1' + right);
            list.add(left + '8' + right);
            list.add(left + '0' + right);
        } else {
            for (char ch : map.keySet()) {
                if (ch == '0') {
                    if(!left.isEmpty()) {
                        helper(map, left +  "0", "0" + right, n - 2, list);
                    }
                } else {
                    helper(map, left + ch, map.get(ch) + right, n - 2, list);
                }
            }
        }
    }
}
