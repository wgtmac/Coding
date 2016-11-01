package com.leetcode;

import java.util.Arrays;

/**
 * 265. Paint House II
 *
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different. You have to
 * paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k
 * cost matrix. For example, costs[0][0] is the cost of painting house 0 with color
 * 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find
 * the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;
        int[][] minCost = new int[n][k];
        System.arraycopy(costs[0], 0, minCost[0], 0, k);

        int[] minL = new int[k], minR = new int[k];
        for (int i = 1; i < n; ++i) {
            for (int l = 0, r = k - 1; l < k; ++l, --r) {
                minL[l] = (l == 0 ? minCost[i - 1][l] : Math.min(minL[l - 1], minCost[i - 1][l]));
                minR[r] = (l == 0 ? minCost[i - 1][r] : Math.min(minR[r + 1], minCost[i - 1][r]));
            }

            for (int j = 0; j < k; ++j) {
                minCost[i][j] = Math.min((j == 0 ? Integer.MAX_VALUE : minL[j - 1]),
                        (j == (k - 1) ? Integer.MAX_VALUE : minR[j + 1])) + costs[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < k; ++j) {
            min = Math.min(min, minCost[n - 1][j]);
        }
        return min;
    }

    public static void main(String[] args) {

        PaintHouseII p = new PaintHouseII();
        int[][] costs = {{1,5,3},{2,9,4}};
        System.out.println(p.minCostII(costs));
    }
}
