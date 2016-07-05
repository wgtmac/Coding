package com.leetcode;

/**
 * 363. Max Sum of Rectangle No Larger Than K
 *
 Given a non-empty 2D matrix matrix and an integer k,
 find the max sum of a rectangle in the matrix such that its sum is no larger than k.

 Example:
 Given matrix = [
 [1,  0, 1],
 [0, -2, 3]
 ]
 k = 2
 The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]]
 is 2 and 2 is the max number no larger than k (k = 2).

 Note:
 The rectangle inside the matrix must have an area > 0.
 What if the number of rows is much larger than the number of columns?
 */
public class MaxSumofRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
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

    public int maxSumSubmatrix_error(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }

        int maxSum = 0, threshold = matrix[0].length;
        for (int i = 0; i < matrix.length; ++i) {
            int[] col = new int[threshold];
            for (int j = i; j < matrix.length; ++j) {
                // init vertical sum
                for (int m = 0; m < threshold; ++m) {
                    col[m] += matrix[j][m];
                }

                // two pointer solution
                int start = 0, end = 0, sum = 0;

                while (end < threshold) {
                    sum += col[end];

                    if (sum == k) {
                        maxSum = k;
                        break;
                    }
                    else if (sum < 0) {
                        start = end + 1;
                        sum = 0;
                    } else if (sum > k) {
                        while (start <= end && sum > k) {
                            sum -= col[start++];
                        }

                        if (start <= end && sum > maxSum) {
                            maxSum = sum;
                        }
                    } else {
                        if (sum > maxSum) {
                            maxSum = sum;
                        }
                    }

                    end++;
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumofRectangleNoLargerThanK m = new MaxSumofRectangleNoLargerThanK();
        System.out.println(m.maxSumSubmatrix(new int[][]{{1,  0, 1}, {0, -2, 3}}, 2));
        System.out.println(m.maxSumSubmatrix(new int[][]{{2, 2, -1}}, 3));
    }
}
