package com.leetcode;

import java.util.*;

/**
 * 322. Coin Change
 *
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make
 * up that amount. If that amount of money cannot be made up by any combination
 * of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {
    // O(amount * coins.length)
    public int coinChange(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, -1);
        minCoins[0] = 0;

        Arrays.sort(coins);
        for (int amnt = 1; amnt <= amount; ++amnt) {
            int min = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (coin > amnt) break;
                if (minCoins[amnt - coin] != -1) {
                    min = Math.min(min, minCoins[amnt - coin] + 1);
                }
            }

            if (min != Integer.MAX_VALUE) minCoins[amnt] = min;
        }

        return minCoins[amount];
    }

    // TLE: O(amount^2)
    public int coinChange_TLE(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, -1);
        minCoins[0] = 0;
        for (int coin : coins) {
            if (coin <= amount) minCoins[coin] = 1;
        }

        for (int amnt = 2; amnt <= amount; ++amnt) {
            int min = Integer.MAX_VALUE;

            for (int part = amnt; part + part >= amnt; part--) {
                if (minCoins[part] != -1 && minCoins[amnt - part] != -1) {
                    min = Math.min(min, minCoins[part] + minCoins[amnt - part]);
                }
            }

            if (min != Integer.MAX_VALUE) minCoins[amnt] = min;
        }

        return minCoins[amount];
    }
}
