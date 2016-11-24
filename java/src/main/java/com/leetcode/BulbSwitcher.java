package com.leetcode;

/**
 * 319. Bulb Switcher
 *
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb. On the third round, you toggle every
 * third bulb (turning on if it's off or turning off if it's on). For the ith
 * round, you toggle every i bulb. For the nth round, you only toggle the last
 * bulb. Find how many bulbs are on after n rounds.
 *
 * Example:
 *
 * Given n = 3.
 *
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 *
 * So you should return 1, because there is only one bulb is on.
 */
public class BulbSwitcher {
    public int bulbSwitch_TLE(int n) {
        // each bulb will be touched n times, n is the number of its factors
        int[] factors = new int[n + 1];

        for (int factor = 1; factor <= n; ++factor) {
            for (int num = factor; num <= n; num += factor) {
                factors[num]++;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; ++i) {
            if (factors[i] % 2 != 0) count++;
        }

        return count;
    }

    public int bulbSwitch(int n) {
        // 1, 4, 9, 16, 25, etc...
        return (int)Math.sqrt(n);
    }
}
