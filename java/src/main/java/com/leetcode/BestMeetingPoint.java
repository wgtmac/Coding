package com.leetcode;

/**
 * 296. Best Meeting Point
 *
 * A group of two or more people wants to meet and minimize the total travel
 * distance. You are given a 2D grid of values 0 or 1, where each 1 marks the
 * home of someone in the group. The distance is calculated using Manhattan
 * Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * For example, given three people living at (0,0), (0,4), and (2,2):
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * The point (0,2) is an ideal meeting point, as the total travel distance of
 * 2+2+2=6 is minimal. So return 6.
 */
public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    for (int m = 0; m < grid.length; ++m) {
                        for (int n = 0; n < grid[0].length; ++n) {
                            dist[m][n] += (Math.abs(m - i) + Math.abs(n - j));
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                min = Math.min(min, dist[i][j]);
            }
        }
        return min;
    }

    /**
     * As long as you have different numbers of people on your left and on your
     * right, moving a little to the side with more people decreases the sum of
     * distances. So to minimize it, you must have equally many people on your
     * left and on your right. Same with above/below.
     *
     * @link: https://discuss.leetcode.com/topic/27722/o-mn-java-2ms
     */
    public int minTotalDistance_faster(int[][] grid) {
        int row = grid.length, col = grid[0].length;

        // rows: horizontal view, each element is the sum of houses on this column
        // cols: same as rows
        int[] rows = new int[col], cols = new int[row];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == 1) {
                    rows[j]++;
                    cols[i]++;
                }
            }
        }

        int dist = 0;
        for (int[] k : new int[][] {rows, cols}) {
            // left to right
            int[] left = new int[k.length], right = new int[k.length];
            for (int i = 0, count = 0; i < k.length; ++i) {
                left[i] = (i == 0 ? 0 : left[i - 1]) + count;
                count += k[i];
            }

            // right to left
            for (int i = k.length - 1, count = 0; i >= 0; --i) {
                right[i] = (i == k.length - 1 ? 0 : right[i + 1]) + count;
                count += k[i];
            }

            // get min
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < k.length; ++i) {
                min = Math.min(min, left[i] + right[i]);
            }

            dist += min;
        }

        return dist;
    }
}
