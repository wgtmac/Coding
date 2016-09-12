package com.leetcode;

/**
 * 122. Best Time to Buy and Sell Stock II
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. 
 * You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 * 
 * Skill: 
 * 找波谷和波峰，然后相减
 * 
 * 更好的做法：greedy，只要增加就算进来
 * */

public class BestTimeToBuyAndSellStockII {
	
    public int maxProfit_Better(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }
	
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int lowest = prices[0], profit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] > prices[i]) {         // decreasing
                profit += (prices[i - 1] - lowest);
                lowest = prices[i];
            }
            
            if (i == prices.length - 1 && lowest < prices[i]) {
                profit += (prices[i] - lowest);
            }
        }
        
        return profit;
    }
}
