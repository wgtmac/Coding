package com.leetcode;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 *
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock multiple
 * times) with the following restrictions:
 *   1. You may not engage in multiple transactions at the same time
 *      (ie, you must sell the stock before you buy again).
 *   2. After you sell your stock, you cannot buy stock on next day.
 *      (ie, cooldown 1 day)
 *
 * Example:
 *   prices = [1, 2, 3, 0, 2]
 *   maxProfit = 3
 *   transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimetoBuyandSellStockwithCooldown {

    /**
     * State machine: 3 states S0=FREE S1=BOUGHT S2=SOLD
     *
     * S0 --  buy  --> S1
     * S0 -- no-op --> S0
     *
     * S1 -- sell  --> S2
     * S1 -- no-op --> S1
     *
     * S2 -- no-op --> S0
     *
     * @link: https://discuss.leetcode.com/topic/30680/share-my-dp-solution-by-state-machine-thinking/2
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int[] s0 = new int[prices.length];
        int[] s1 = new int[prices.length];
        int[] s2 = new int[prices.length];

        s1[0] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s0[i - 1] - prices[i], s1[i - 1]);
            s2[i] = s1[i - 1] + prices[i];
        }

        return Math.max(s0[prices.length - 1], s2[prices.length - 1]);
    }
}
