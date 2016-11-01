package com.leetcode;

/**
 * 256. Paint House
 *
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain color
 * is different. You have to paint all the houses such that no two adjacent houses
 * have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3
 * cost matrix. For example, costs[0][0] is the cost of painting house 0 with color
 * red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs.length == 0) return 0;
        int[][] minCost = new int[costs.length][3];
        System.arraycopy(costs[0], 0, minCost[0], 0, 3);

        for (int i = 1; i < costs.length; ++i) {
            minCost[i][0] = Math.min(minCost[i - 1][1], minCost[i - 1][2]) + costs[i][0];
            minCost[i][1] = Math.min(minCost[i - 1][0], minCost[i - 1][2]) + costs[i][1];
            minCost[i][2] = Math.min(minCost[i - 1][0], minCost[i - 1][1]) + costs[i][2];
        }

        return Math.min(minCost[costs.length - 1][0],
                Math.min(minCost[costs.length - 1][1], minCost[costs.length - 1][2]));
    }
}
