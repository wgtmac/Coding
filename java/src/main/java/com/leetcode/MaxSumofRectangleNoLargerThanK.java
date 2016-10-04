package com.leetcode;

import java.util.TreeSet;

/**
 * 363. Max Sum of Rectangle No Larger Than K
 *
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a
 * rectangle in the matrix such that its sum is no larger than k.
 *
 * Example:
 * Given matrix = [
 * [1,  0, 1],
 * [0, -2, 3]
 * ]
 * k = 2
 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]]
 * is 2 and 2 is the max number no larger than k (k = 2).
 *
 * Note:
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 */
public class MaxSumofRectangleNoLargerThanK {
    public int maxSumSubmatrix_On4(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }

        // init auxiliary matrix
        int h = matrix.length, v = matrix[0].length;
        int[][] sum = new int[h][v];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < v; ++j) {
                if (i == 0 && j == 0) {
                    sum[i][j] = matrix[i][j];
                } else if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + matrix[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + matrix[i][j];
                } else {
                    sum[i][j] = matrix[i][j] + sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
                }
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int h_i = 0; h_i < h; h_i++) {
            for (int h_j = h_i; h_j < h; h_j++) {
                for (int v_i = 0; v_i < v; v_i++) {
                    for (int v_j = v_i; v_j < v; v_j++) {
                        int tmpSum;
                        if (h_i == 0 && v_i == 0) {
                            tmpSum = sum[h_j][v_j];
                        } else if (h_i == 0) {
                            tmpSum = sum[h_j][v_j] - sum[h_j][v_i - 1];
                        } else if (v_i == 0) {
                            tmpSum = sum[h_j][v_j] - sum[h_i - 1][v_j];
                        } else {
                            tmpSum = sum[h_j][v_j] - sum[h_j][v_i - 1] - sum[h_i - 1][v_j] + sum[h_i - 1][v_i - 1];
                        }

                        if (tmpSum > maxSum && tmpSum <= k) {
                            maxSum = tmpSum;
                        }

                        if (maxSum == k) {
                            return k;
                        }
                    }
                }
            }
        }

        return maxSum;
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE, row = matrix.length, col = matrix[0].length;
        if (row <= col) {
            for (int startRow = 0; startRow < row; ++startRow) {
                int[] colSum = new int[col];
                for (int endRow = startRow; endRow < row; ++endRow) {
                    for (int c = 0; c < col; ++c)
                        colSum[c] += matrix[endRow][c];

                    int sum = maxSumSubarray(colSum, k);
                    maxSum = Math.max(maxSum, sum);
                    if (maxSum == k) return k;
                }
            }
        } else {
            for (int startCol = 0; startCol < col; ++startCol) {
                int[] rowSum = new int[row];
                for (int endCol = startCol; endCol < col; ++endCol) {
                    for (int r = 0; r < row; ++r)
                        rowSum[r] += matrix[r][endCol];

                    int sum = maxSumSubarray(rowSum, k);
                    maxSum = Math.max(maxSum, sum);
                    if (maxSum == k) return k;
                }
            }
        }

        return maxSum;
    }

    private int maxSumSubarray(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE, sum = 0;
        TreeSet<Integer> set = new TreeSet<Integer>() {{add(0);}};

        for (int num : nums) {
            sum += num;
            // find a num that satisfies (sum-num)<=k, so num>=(sum-k)
            Integer prevSum = set.ceiling(sum - k);
            if (prevSum != null)
                maxSum = Math.max(maxSum, sum - prevSum);
            set.add(sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumofRectangleNoLargerThanK m = new MaxSumofRectangleNoLargerThanK();
        System.out.println(m.maxSumSubmatrix(new int[][]{{1,  0, 1}, {0, -2, 3}}, 2));
        System.out.println(m.maxSumSubmatrix(new int[][]{{2, 2, -1}}, 3));
    }
}
