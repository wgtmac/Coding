package com.leetcode;

/**
 * 308. Range Sum Query 2D - Mutable
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DMutable {
    public class NumMatrix {
        private int[][] segmentTree;
        private int left, right;

        public NumMatrix(int[][] matrix) {
            if (matrix.length > 0 && matrix[0].length > 0) {
                left = 0;
                right = matrix[0].length - 1;
                int height = (int) Math.ceil(Math.log(matrix[0].length) / Math.log(2)) + 1;
                segmentTree = new int[matrix.length][(int) Math.pow(2, height) - 1];
                for (int i = 0; i < matrix.length; ++i)
                    build(matrix, i, left, right, 0);
            }
        }

        private int build(int[][] matrix, int row, int start, int end, int index) {
            if (start == end) {
                segmentTree[row][index] = matrix[row][start];
            } else {
                int mid = start + (end - start) / 2;
                segmentTree[row][index] = build(matrix, row, start, mid, index * 2 + 1) +
                        build(matrix, row, mid + 1, end, index * 2 + 2);
            }
            return segmentTree[row][index];
        }

        public void update(int row, int col, int val) {
            update(row, col, val, 0, left, right);
        }

        private int update(int row, int rangeIndex, int val, int treeIndex, int start, int end) {
            if (start == end && start == rangeIndex) {
                segmentTree[row][treeIndex] = val;
            } else {
                int mid = start + (end - start) / 2;
                if (rangeIndex <= mid)
                    update(row, rangeIndex, val, treeIndex * 2 + 1, start, mid);
                else
                    update(row, rangeIndex, val, treeIndex * 2 + 2, mid + 1, end);

                segmentTree[row][treeIndex] = segmentTree[row][2 * treeIndex + 1]
                        + segmentTree[row][2 * treeIndex + 2];
            }
            return segmentTree[row][treeIndex];
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int row = row1; row <= row2; ++row) {
                sum += query(row, col1, col2, 0, left, right);
            }
            return sum;
        }

        private int query(int row, int queryStart, int queryEnd, int treeIndex, int rangeStart, int rangeEnd) {
            if (queryStart <= rangeStart && rangeEnd <= queryEnd) {
                return segmentTree[row][treeIndex];
            }

            int rangeMid = rangeStart + (rangeEnd - rangeStart) / 2;
            if (queryEnd <= rangeMid)
                return query(row, queryStart, queryEnd, treeIndex * 2 + 1, rangeStart, rangeMid);
            if (queryStart >= rangeMid + 1)
                return query(row, queryStart, queryEnd, treeIndex * 2 + 2, rangeMid + 1, rangeEnd);

            return query(row, queryStart, rangeMid, treeIndex * 2 + 1, rangeStart, rangeMid) +
                    query(row, rangeMid + 1, queryEnd, treeIndex * 2 + 2, rangeMid + 1, rangeEnd);
        }

    }


    // Your NumMatrix object will be instantiated and called as such:
    // NumMatrix numMatrix = new NumMatrix(matrix);
    // numMatrix.sumRegion(0, 1, 2, 3);
    // numMatrix.update(1, 1, 10);
    // numMatrix.sumRegion(1, 2, 3, 4);
}
