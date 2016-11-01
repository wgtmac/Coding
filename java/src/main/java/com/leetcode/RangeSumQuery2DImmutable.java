package com.leetcode;

/**
 * 304. Range Sum Query 2D - Immutable
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 */
public class RangeSumQuery2DImmutable {
    public class NumMatrix {

        int[][] sum;

        public NumMatrix(int[][] matrix) {
            sum = matrix;
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 1; j < matrix[0].length; ++j) {
                    sum[i][j] += sum[i][j - 1];
                }
            }

            for (int j = 0; matrix.length > 0 && j < matrix[0].length; ++j) {
                for (int i = 1; i < matrix.length; ++i) {
                    sum[i][j] += sum[i - 1][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2][col2]
                    - (col1 - 1 >= 0 ? sum[row2][col1 - 1] : 0)
                    - (row1 - 1 >= 0 ? sum[row1 - 1][col2] : 0)
                    + ((col1 - 1 >= 0 && row1 - 1 >= 0) ? sum[row1 - 1][col1 - 1] : 0);
        }
    }
}
