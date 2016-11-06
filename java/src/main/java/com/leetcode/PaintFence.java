package com.leetcode;

/**
 * 276. Paint Fence
 *
 * There is a fence with n posts, each post can be painted with one of the k colors.
 *
 * You have to paint all the posts such that no more than two adjacent fence posts
 * have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 * n and k are non-negative integers.
 *
 * 3 1
 *
 * 1 0 0
 * 0 1 0
 * 0 0
 */
public class PaintFence {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        int[][] f = new int[n][2];   // 0: diff than prev, 1: same as prev

        f[0][0] = k;
        for (int i = 1; i < n; ++i) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1]) * (k - 1);   // use diff color
            f[i][1] = f[i - 1][0];
        }

        return f[n - 1][0] + f[n - 1][1];
    }

    public static void main(String[] args) {
        PaintFence p = new PaintFence();
        System.out.println(p.numWays(4, 2));
    }
}
