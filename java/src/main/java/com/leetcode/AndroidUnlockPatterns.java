package com.leetcode;

import java.util.*;

/**
 * 351. Android Unlock Patterns
 *
 * Given an Android 3x3 key lock screen and two integers m and n, where
 * 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock
 * screen, which consist of minimum of m keys and maximum n keys.
 *
 * Rules for a valid pattern:
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any
 * other keys, the other keys must have previously selected in the pattern.
 * No jumps through non selected key is allowed.
 * The order of keys used matters.
 *
 * Explanation:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 *
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 *
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 *
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 *
 * Example:
 * Given m = 1, n = 1, return 9.
 */
public class AndroidUnlockPatterns {

    public static void main(String[] args) {
        AndroidUnlockPatterns a = new AndroidUnlockPatterns();
        System.out.println(a.numberOfPatterns(4, 9));
    }


    private static List<Integer> ALL_DIGITS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public int numberOfPatterns(int m, int n) {
        // init mapping for two-step digits
        Map<Integer, Set<Integer>> map = initTwoStepDigits();
        Set<Integer> path = new HashSet<>();

        int[] count = {0};
        int cnt = 0;

        // 1, 3, 7, 9
        path.add(1);
        move(path, map, count, m, n, 1);
        path.clear();

        // 2, 4, 6, 8
        path.add(2);
        move(path, map, count, m, n, 2);
        path.clear();

        cnt += count[0] * 4;
        count[0] = 0;

        // 5
        path.add(5);
        move(path, map, count, m, n, 5);

        return count[0] + cnt;
    }

    private void move(Set<Integer> path, Map<Integer, Set<Integer>> map,
                      int[] count, int m, int n, int lastDigit) {
        if (m <= path.size() && path.size() <= n) count[0]++;
        if (path.size() == n) return;

        Set<Integer> available = new HashSet<>(ALL_DIGITS);
        if (lastDigit != 0) {
            // remove unreachable two-step digit
            for (int twoStepDigit : map.get(lastDigit)) {
                if (!path.contains((lastDigit + twoStepDigit) / 2)) {
                    available.remove(twoStepDigit);
                }
            }

            // remove all matched digits
            available.removeAll(path);
        }

        for (int digit : available) {
            path.add(digit);
            move(path, map, count, m, n, digit);
            path.remove(digit);
        }
    }

    /**
     * key: src digit, value: dst digits that can't be reached directly
     */
    private Map<Integer, Set<Integer>> initTwoStepDigits() {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= 9; ++i) {
            map.put(i, new HashSet<>());
            if (i % 2 == 0)
                map.get(i).add(10 - i);
            else if (i != 5) {
                map.get(i).addAll(Arrays.asList(1, 3, 7, 9));
                map.get(i).remove(i);
            }
        }
        return map;
    }
}
